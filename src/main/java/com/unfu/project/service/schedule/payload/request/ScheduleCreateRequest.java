package com.unfu.project.service.schedule.payload.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class ScheduleCreateRequest {

    @NotNull
    private Long teacherId;

    @NotNull
    private Long groupId;

    @NotNull
    private Long subjectId;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalTime startTime;

    @NotNull
    private LocalTime endTime;

    @NotNull
    @Valid
    private ScheduleRestrictionRequest restriction;

    private Integer interval;
}
