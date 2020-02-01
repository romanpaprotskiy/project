package com.unfu.project.service.authentication.payload.response;

import com.unfu.project.domain.authentication.enumeration.Role;
import lombok.Data;

import java.io.Serializable;

@Data
public class AuthorityResponse implements Serializable {

    private static final long serialVersionUID = -8004564388537535092L;

    private Role authority;
}
