package com.unfu.project.security;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.StoredCredential;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.oauth2.Oauth2;
import com.google.api.services.oauth2.model.Userinfoplus;
import com.unfu.project.config.GoogleSecrets;
import com.unfu.project.entity.User;
import com.unfu.project.payload.request.AuthRequest;
import com.unfu.project.payload.response.AuthResponse;
import com.unfu.project.repository.UserRepository;
import com.unfu.project.service.mapper.UserMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;

@Service
public class AuthenticationService {


    private final JwtTokenProvider jwtTokenProvider;

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final AuthorizationCodeFlow codeFlow;

    private final GoogleSecrets googleSecrets;

    public AuthenticationService(JwtTokenProvider jwtTokenProvider, UserRepository userRepository, UserMapper userMapper, AuthorizationCodeFlow codeFlow, GoogleSecrets googleSecrets) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.codeFlow = codeFlow;
        this.googleSecrets = googleSecrets;
    }


    public AuthResponse authorizeOrRegister(AuthRequest request) throws IOException {

        TokenResponse tokenResponse = codeFlow.newTokenRequest(request.getAuthCode())
                .setGrantType("authorization_code")
                .setRedirectUri(request.getRedirectUri())
                .execute();

        GoogleCredential credential = new GoogleCredential.Builder()
                .setClientSecrets(googleSecrets.getClientId(), googleSecrets.getClientSecret())
                .setJsonFactory(new JacksonFactory())
                .setTransport(new NetHttpTransport())
                .build();

        credential.setFromTokenResponse(tokenResponse);

        Oauth2 oauth2 = new Oauth2.Builder(new NetHttpTransport(), new JacksonFactory(), credential)
                .setApplicationName("Project").build();
        Userinfoplus userProfile = oauth2.userinfo().get().execute();

        StoredCredential storedCredential = new StoredCredential(credential);
        codeFlow.getCredentialDataStore().set(userProfile.getEmail(), storedCredential);

        if (userRepository.existsByEmail(userProfile.getEmail())) {
            User user = userRepository.findByEmail(userProfile.getEmail())
                    .orElseThrow(() -> new UsernameNotFoundException("Email not found"));
            return getResponse(user);
        }
        User user = createUser(userProfile);
        return getResponse(user);
    }

    private AuthResponse getResponse(User user) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        JWT jwt = jwtTokenProvider.generateToken(authentication);
        return AuthResponse.builder()
                .accessToken(jwt.getToken())
                .expiryDate(jwt.getExpiryDate())
                .user(userMapper.map(user))
                .build();
    }

    private User createUser(Userinfoplus profile) {
        User user = new User();
        user.setFirstName(profile.getFamilyName());
        user.setLastName(profile.getGivenName());
        user.setActive(true);
        user.setEmail(profile.getEmail());
        user.setUserId(profile.getId());//TODO define authorities
        return userRepository.save(user);
    }
}
