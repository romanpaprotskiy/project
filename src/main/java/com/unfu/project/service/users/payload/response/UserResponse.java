package com.unfu.project.service.users.payload.response;

import com.unfu.project.domain.users.enumeration.Gender;
import com.unfu.project.service.authentication.payload.response.AuthorityResponse;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class UserResponse {

    private Long id;

    private String pictureUrl;

    private String firstName;

    private String lastName;

    private String email;

    private Gender gender;

    private LocalDate birthDate;

    private String phone;

    private String skypeId;

    private String userId;

    private Boolean active;

    private Set<AuthorityResponse> authorities;
}
