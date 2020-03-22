package com.unfu.project.service.users.payload.response;

import com.unfu.project.service.managerment.payload.response.GroupResponse;
import lombok.Data;

import java.time.LocalDate;

@Data
public class StudentResponse {

    private Long id;

    private Long userId;

    private LocalDate dateOfEnroll;

    private GroupResponse group;
}
