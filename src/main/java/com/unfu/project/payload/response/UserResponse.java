package com.unfu.project.payload.response;

import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Data
public class UserResponse implements Serializable {

    private static final long serialVersionUID = -2493840840358134223L;

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private Set<AuthorityResponse> authorities;
}
