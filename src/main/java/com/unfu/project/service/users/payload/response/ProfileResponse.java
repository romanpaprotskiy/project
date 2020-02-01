package com.unfu.project.service.users.payload.response;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class ProfileResponse implements Serializable {

    private static final long serialVersionUID = 437116005867057783L;

    private UserResponse user;
}
