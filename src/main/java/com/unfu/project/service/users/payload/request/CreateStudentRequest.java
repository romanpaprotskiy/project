package com.unfu.project.service.users.payload.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public final class CreateStudentRequest {

    @NotNull(message = "User is required")
    private Long userId;

    @NotNull(message = "Group is required")
    private Long groupId;

    private LocalDate dateOfEnroll;
}
