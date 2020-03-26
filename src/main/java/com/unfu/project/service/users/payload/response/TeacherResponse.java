package com.unfu.project.service.users.payload.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TeacherResponse {

    private Long id;

    private String pictureUrl;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private String scienceTitleName;

    private LocalDate employedFrom;

    private LocalDate birthDate;
}
