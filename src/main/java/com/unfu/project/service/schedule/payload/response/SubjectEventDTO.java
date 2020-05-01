package com.unfu.project.service.schedule.payload.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public final class SubjectEventDTO {

    private Long subjectId;

    private Long groupId;

    private String groupName;

    private String subjectName;

    private LocalDateTime startDateTime;

    private LocalDateTime endDateTime;
}
