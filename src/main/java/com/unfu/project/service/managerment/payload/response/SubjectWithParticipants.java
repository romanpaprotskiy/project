package com.unfu.project.service.managerment.payload.response;

import com.unfu.project.service.users.payload.response.TeacherResponse;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public final class SubjectWithParticipants {

    private SubjectResponse subject;

    private List<TeacherResponse> teachers;

    private List<GroupWithStudents> groups;
}
