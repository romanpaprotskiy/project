package com.unfu.project.security;

import com.unfu.project.exception.PermissionsDeniedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class SocialConnectionService {

    private final Logger log = LoggerFactory.getLogger(SocialConnectionService.class);

    private final GoogleConnectionFactory googleConnectionFactory;

    public SocialConnectionService(GoogleConnectionFactory googleConnectionFactory) {
        this.googleConnectionFactory = googleConnectionFactory;
    }

    public Connection<?> getConnection(String providerId, String accessToken) {
        AccessGrant accessGrant = new AccessGrant(accessToken);
        OAuth2ConnectionFactory connectionFactory;
        if (Objects.equals("google", providerId)) {
            connectionFactory = googleConnectionFactory;
        } else {
            throw new RuntimeException("Allowed only Google oauth authorization");
        }
        try {
            return (Connection<?>) connectionFactory.createConnection(accessGrant);
        } catch (Exception e) {
            log.debug("socialConnectionService exception:");
            e.printStackTrace();
            throw new PermissionsDeniedException();
        }
    }
}
