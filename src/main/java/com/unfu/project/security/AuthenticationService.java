package com.unfu.project.security;

import com.unfu.project.entity.User;
import com.unfu.project.payload.request.AuthRequest;
import com.unfu.project.payload.response.AuthResponse;
import com.unfu.project.repository.UserRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.UserProfile;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final SocialConnectionService socialConnectionService;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserRepository userRepository;

    public AuthenticationService(SocialConnectionService socialConnectionService, JwtTokenProvider jwtTokenProvider, UserRepository userRepository) {
        this.socialConnectionService = socialConnectionService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRepository = userRepository;
    }


    public AuthResponse authorizeOrRegister(AuthRequest request) {

        Connection<?> connection = socialConnectionService.getConnection(request.getAccessToken());
        UserProfile userProfile = connection.fetchUserProfile();
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
                .build();
    }

    private User createUser(UserProfile profile) {
        User user = User.builder()
                .firstName(profile.getFirstName())
                .lastName(profile.getLastName())
                .active(true)
                .email(profile.getEmail())
                .username(profile.getUsername())
                .userId(profile.getId())
                .build();//TODO define authorities
        return userRepository.save(user);
    }
}
