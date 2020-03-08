package com.unfu.project.service.managerment.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GroupStudentsCount {

    private final Long groupId;

    private final Long countOfStudents;
}
