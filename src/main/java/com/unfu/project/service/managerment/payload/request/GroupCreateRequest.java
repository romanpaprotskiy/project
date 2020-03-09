package com.unfu.project.service.managerment.payload.request;

import lombok.Data;

@Data
public class GroupCreateRequest {

    private String name;

    private Long parentGroupId;

    private Long teacherId;
}
