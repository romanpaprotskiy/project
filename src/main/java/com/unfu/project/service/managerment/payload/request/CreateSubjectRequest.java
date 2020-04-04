package com.unfu.project.service.managerment.payload.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CreateSubjectRequest {

    @NotNull
    private String name;

    @NotNull
    private String courseNumber;
}
