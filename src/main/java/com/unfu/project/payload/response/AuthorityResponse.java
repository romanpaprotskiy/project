package com.unfu.project.payload.response;

import com.unfu.project.entity.constants.Role;
import lombok.Data;

import java.io.Serializable;

@Data
public class AuthorityResponse implements Serializable {

    private static final long serialVersionUID = -8004564388537535092L;

    private Role authority;
}
