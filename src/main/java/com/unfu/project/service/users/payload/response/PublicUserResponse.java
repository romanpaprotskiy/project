package com.unfu.project.service.users.payload.response;

import lombok.Data;

@Data
public class PublicUserResponse {

    private Long id;

    private String pictureUrl;

    private String firstName;

    private String lastName;

    private String email;
}
