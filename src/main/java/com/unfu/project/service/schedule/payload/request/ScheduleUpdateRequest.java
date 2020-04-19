package com.unfu.project.service.schedule.payload.request;

import lombok.Data;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public final class ScheduleUpdateRequest {

    private Long id;

    private Long teacherId;

    private Long groupId;

    private Long subjectId;

    private Long googleEventId;

    private String eventId;

    private LocalDate startDate;

    private LocalTime startTime;

    private LocalTime endTime;

    private Integer interval;

    @Valid
    private ScheduleRestrictionRequest restriction;
}
