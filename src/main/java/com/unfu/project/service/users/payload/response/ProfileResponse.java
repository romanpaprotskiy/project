package com.unfu.project.service.users.payload.response;

import com.unfu.project.service.managerment.payload.response.SubjectResponse;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public final class ProfileResponse {

    private final UserResponse user;

    private final StudentResponse student;

    private final List<SubjectResponse> subjects;
}
