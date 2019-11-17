package com.unfu.project.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public final class GoogleSecrets {

    private final String clientId;

    private final String clientSecret;

    public GoogleSecrets(@Value("${google.clientId}") String clientId,
                         @Value("${google.clientSecret}") String clientSecret) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    public String getClientId() {
        return clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }
}
