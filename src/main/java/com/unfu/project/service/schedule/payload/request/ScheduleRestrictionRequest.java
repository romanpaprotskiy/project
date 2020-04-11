package com.unfu.project.service.schedule.payload.request;

import com.unfu.project.service.schedule.enumeration.ScheduleRestrictionType;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public final class ScheduleRestrictionRequest {

    @NotNull
    private ScheduleRestrictionType restrictionType;

    private Integer count;

    private LocalDate endDate;
}
