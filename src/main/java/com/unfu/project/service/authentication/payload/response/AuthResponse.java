package com.unfu.project.service.authentication.payload.response;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Data
@Builder
public class AuthResponse implements Serializable {

    private static final long serialVersionUID = -4903260774242163865L;

    private String accessToken;

    private String refreshToken;

    private Date expiryDate;

    private Set<AuthorityResponse> authorities;
}
