package com.unfu.project.service.managerment.payload.response;

import com.unfu.project.service.events.payload.response.GoogleRecurrentEventResponse;
import com.unfu.project.service.users.payload.response.StudentUserResponse;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public final class GroupWithStudents {

    private Long id;

    private String name;

    private GoogleRecurrentEventResponse schedule;

    private List<StudentUserResponse> students;
}
