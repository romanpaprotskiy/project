package com.unfu.project.payload.response;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
public class AuthResponse implements Serializable {

    private static final long serialVersionUID = -4903260774242163865L;

    private String accessToken;

    private String refreshToken;

    private Date expiryDate;

    private UserResponse user;
}
