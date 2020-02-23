package com.unfu.project.service.users.payload.response;

import com.unfu.project.service.managerment.payload.response.GroupResponse;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class StudentUserResponse implements Serializable {

    private static final long serialVersionUID = -8592012367261511683L;

    private Long id;

    private UserResponse user;

    private LocalDate dateOfEnroll;

    private GroupResponse group;
}
