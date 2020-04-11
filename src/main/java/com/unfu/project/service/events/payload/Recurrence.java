package com.unfu.project.service.events.payload;

import com.unfu.project.service.events.enumeration.RecurrenceFrequency;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class Recurrence {

    private final RecurrenceFrequency frequency;

    private final Integer count;

    private final LocalDate endDate;

    private final Integer interval;
}
