package com.unfu.project.payload.response;

import com.unfu.project.entity.constants.Gender;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Data
public class UserResponse implements Serializable {

    private static final long serialVersionUID = -2493840840358134223L;

    private Long id;

    private String pictureUrl;

    private String firstName;

    private String lastName;

    private String email;

    private Gender gender;

    private LocalDate birthDate;

    private String phone;

    private String userId;

    private Boolean active;

    private Set<AuthorityResponse> authorities;
}
