package com.unfu.project.service.authentication.data;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class GoogleDataStore {

    private Map<String, GoogleData> googleDataMap = new HashMap<>();

    public GoogleCredential get(String email) throws IOException {
        var googleData = googleDataMap.get(email);
        var expiryDate = googleData.getExpiryDate();
        if (new Date().after(expiryDate)) {
            googleData.getGoogleCredential().refreshToken();
            var instant = new Date()
                    .toInstant().plusSeconds(googleData.getGoogleCredential().getExpiresInSeconds());
            googleData.setExpiryDate(Date.from(instant));
            googleDataMap.put(email, googleData);
        }
        return googleData.getGoogleCredential();
    }

    public void set(String email, GoogleCredential googleCredential) {
        var data = GoogleData.builder()
                .googleCredential(googleCredential)
                .expiryDate(Date.from(new Date().toInstant().plusSeconds(googleCredential.getExpiresInSeconds())))
                .build();
        googleDataMap.put(email, data);
    }

}
