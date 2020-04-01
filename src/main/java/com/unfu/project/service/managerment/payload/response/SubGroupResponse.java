package com.unfu.project.service.managerment.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubGroupResponse {

    private Long id;

    private String name;

    private GroupResponse group;
}
