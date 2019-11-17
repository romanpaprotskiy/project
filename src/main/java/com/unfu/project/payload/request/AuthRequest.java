package com.unfu.project.payload.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class AuthRequest implements Serializable {

    private static final long serialVersionUID = -776759156781738040L;

    @NotNull
    private String authCode;

    private String redirectUri;
}
