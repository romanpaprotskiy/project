package com.unfu.project.service.authentication.data;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class JWT implements Serializable {

    private static final long serialVersionUID = -7866533094397611327L;

    private String token;

    private Date expiryDate;

    public JWT(String token, Date expiryDate) {
        this.token = token;
        this.expiryDate = expiryDate;
    }
}
