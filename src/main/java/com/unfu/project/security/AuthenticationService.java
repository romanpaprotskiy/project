package com.unfu.project.security;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.oauth2.Oauth2;
import com.google.api.services.oauth2.model.Userinfoplus;
import com.unfu.project.config.GoogleSecrets;
import com.unfu.project.entity.User;
import com.unfu.project.entity.constants.Role;
import com.unfu.project.payload.exception.PermissionsDeniedException;
import com.unfu.project.payload.request.AuthRequest;
import com.unfu.project.payload.response.AuthResponse;
import com.unfu.project.repository.AuthorityRepository;
import com.unfu.project.repository.UserRepository;
import com.unfu.project.service.mapper.AuthorityMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class AuthenticationService {


    private final JwtTokenProvider jwtTokenProvider;

    private final UserRepository userRepository;

    private final AuthorizationCodeFlow codeFlow;

    private final GoogleSecrets googleSecrets;

    private final AuthorityRepository authorityRepository;

    private final AuthorityMapper authorityMapper;

    private final GoogleDataStore googleDataStore;

    public AuthenticationService(JwtTokenProvider jwtTokenProvider, UserRepository userRepository,
                                 AuthorizationCodeFlow codeFlow, GoogleSecrets googleSecrets, AuthorityRepository authorityRepository, AuthorityMapper authorityMapper, GoogleDataStore googleDataStore) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRepository = userRepository;
        this.codeFlow = codeFlow;
        this.googleSecrets = googleSecrets;
        this.authorityRepository = authorityRepository;
        this.authorityMapper = authorityMapper;
        this.googleDataStore = googleDataStore;
    }


    public AuthResponse authorizeOrRegister(AuthRequest request) throws IOException {
        var tokenResponse = getTokenResponse(request, codeFlow);
        var credential = getGoogleCredential(googleSecrets);
        credential.setFromTokenResponse(tokenResponse);
        var userProfile = getUserInfoPlus(credential);
        googleDataStore.set(userProfile.getEmail(), credential);
        if (userRepository.existsByEmail(userProfile.getEmail())) {
            User user = userRepository.findByEmail(userProfile.getEmail())
                    .orElseThrow(() -> new UsernameNotFoundException("Email not found"));
            return getResponse(user);
        }
        var user = createUser(userProfile);
        return getResponse(user);
    }

    private Userinfoplus getUserInfoPlus(GoogleCredential credential) throws IOException {
        var oauth2 = new Oauth2.Builder(new NetHttpTransport(), new JacksonFactory(), credential)
                .setApplicationName("Project").build();
        return oauth2.userinfo().get().execute();
    }

    private TokenResponse getTokenResponse(AuthRequest request, AuthorizationCodeFlow codeFlow) throws IOException {
        return codeFlow.newTokenRequest(request.getAuthCode())
                .setGrantType("authorization_code")
                .setRedirectUri(request.getRedirectUri())
                .execute();
    }

    private GoogleCredential getGoogleCredential(GoogleSecrets googleSecrets) {
        return new GoogleCredential.Builder()
                .setClientSecrets(googleSecrets.getClientId(), googleSecrets.getClientSecret())
                .setJsonFactory(new JacksonFactory())
                .setTransport(new NetHttpTransport())
                .build();
    }

    private AuthResponse getResponse(User user) {
        var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        var jwt = jwtTokenProvider.generateToken(authentication);
        return AuthResponse.builder()
                .accessToken(jwt.getToken())
                .expiryDate(jwt.getExpiryDate())
                .authorities(authorityMapper.map(user.getAuthorities()))
                .build();
    }

    private User createUser(Userinfoplus profile) {
        var user = new User();
        user.setFirstName(profile.getGivenName());
        user.setLastName(profile.getFamilyName());
        user.setActive(true);
        user.setEmail(profile.getEmail());
        user.setUserId(profile.getId());
        var authority = authorityRepository.findByAuthority(Role.GUEST)
                .orElseThrow(PermissionsDeniedException::new);
        user.addAuthority(authority);
        return userRepository.save(user);
    }
}
