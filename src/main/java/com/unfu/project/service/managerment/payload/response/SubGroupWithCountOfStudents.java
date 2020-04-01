package com.unfu.project.service.managerment.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public final class SubGroupWithCountOfStudents {

    private Long id;

    private String name;

    private Long countOfStudents;
}
