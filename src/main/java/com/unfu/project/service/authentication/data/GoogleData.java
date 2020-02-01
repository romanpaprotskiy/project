package com.unfu.project.service.authentication.data;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
public class GoogleData implements Serializable {

    private static final long serialVersionUID = 407041441078010586L;

    private Date expiryDate;

    private GoogleCredential googleCredential;
}
