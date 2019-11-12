package com.unfu.project.security;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.oauth2.Oauth2;
import com.google.api.services.oauth2.model.Userinfoplus;
import com.unfu.project.entity.User;
import com.unfu.project.payload.request.AuthRequest;
import com.unfu.project.payload.response.AuthResponse;
import com.unfu.project.repository.UserRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class AuthenticationService {


    private final JwtTokenProvider jwtTokenProvider;

    private final UserRepository userRepository;

    public AuthenticationService(JwtTokenProvider jwtTokenProvider, UserRepository userRepository) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRepository = userRepository;
    }


    public AuthResponse authorizeOrRegister(AuthRequest request) throws IOException {

        GoogleCredential credential = new GoogleCredential().setAccessToken(request.getAccessToken());
        Oauth2 oauth2 = new Oauth2.Builder(new NetHttpTransport(), new JacksonFactory(), credential)
                .setApplicationName("Project").build();
        Userinfoplus userProfile = oauth2.userinfo().get().execute();

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
                .email(user.getEmail())
                .build();
    }

    private User createUser(Userinfoplus profile) {
        User user = new User();
        user.setFirstName(profile.getFamilyName());
        user.setLastName(profile.getGivenName());
        user.setActive(true);
        user.setEmail(profile.getEmail());
        user.setUsername(profile.getName());
        user.setUserId(profile.getId());//TODO define authorities
        return userRepository.save(user);
    }
}
