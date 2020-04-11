package com.unfu.project.service.events.payload.request;

import com.unfu.project.service.events.enumeration.RecurrenceFrequency;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public final class CreateRecurrentEvent {

    private final String summary;

    private final List<String> emails;

    private final String location;

    private final LocalDateTime startDate;

    private final LocalDateTime endDate;

    private final RecurrenceFrequency frequency;

    private final Integer count;

    private final LocalDate endDateOfEvents;

    private final Integer interval;
}
