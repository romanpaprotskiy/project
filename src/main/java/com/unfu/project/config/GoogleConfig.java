package com.unfu.project.config;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.MemoryDataStoreFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.oauth2.Oauth2Scopes;
import com.unfu.project.service.authentication.data.GoogleDataStore;
import com.unfu.project.service.authentication.util.SecurityUtils;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Scope;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

@Configuration
@AllArgsConstructor
public class GoogleConfig {

    private final GoogleSecrets googleSecrets;

    @Bean
    public AuthorizationCodeFlow authorizationCodeFlow() throws IOException {
        return new GoogleAuthorizationCodeFlow.Builder(
                new NetHttpTransport(), new JacksonFactory(),
                googleSecrets.getClientId(), googleSecrets.getClientSecret(),
                List.of(Oauth2Scopes.USERINFO_PROFILE, Oauth2Scopes.USERINFO_EMAIL, CalendarScopes.CALENDAR))
                .setDataStoreFactory(new MemoryDataStoreFactory())
                .setAccessType("offline")
                .build();
    }

    @Bean
    public GoogleIdTokenVerifier googleIdTokenVerifier() {
        return new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new JacksonFactory())
                .setAudience(List.of(googleSecrets.getClientId()))
                .build();
    }
}
