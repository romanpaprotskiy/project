package com.unfu.project.payload.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class AuthRequest implements Serializable {

    private static final long serialVersionUID = -776759156781738040L;

    private String accessToken;
}
