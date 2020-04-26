package com.unfu.project.service.users.payload.response;

import com.unfu.project.service.events.payload.response.EventResponse;
import com.unfu.project.service.managerment.payload.response.SubjectDTO;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public final class ProfileResponse {

    private final UserResponse user;

    private final StudentDTO student;

    private final List<SubjectDTO> subjects;

    private final List<EventResponse> events;
}
