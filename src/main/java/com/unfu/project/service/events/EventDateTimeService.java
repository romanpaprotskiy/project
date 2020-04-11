package com.unfu.project.service.events;

import com.google.api.services.calendar.model.EventDateTime;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface EventDateTimeService {

    EventDateTime fromLocalDate(LocalDate date);

    EventDateTime fromLocalDateTime(LocalDateTime date);
}
