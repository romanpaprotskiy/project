package com.unfu.project.service.events.impl;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.EventDateTime;
import com.unfu.project.service.events.EventDateTimeService;
import com.unfu.project.service.events.payload.GoogleDateTimeFormatter;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
public class EventDateTimeServiceImpl implements EventDateTimeService {

    private final String TIMEZONE;

    public EventDateTimeServiceImpl(Environment environment) {
        this.TIMEZONE = environment.getProperty("date-time.timezone");
    }

    @Override
    public EventDateTime fromLocalDate(LocalDate date) {
        DateTime dateTime = GoogleDateTimeFormatter.format(date, TIMEZONE);
        return new EventDateTime()
                .setDate(dateTime)
                .setTimeZone(TIMEZONE);
    }

    @Override
    public EventDateTime fromLocalDateTime(LocalDateTime date) {
        DateTime dateTime = GoogleDateTimeFormatter.format(date, TIMEZONE);
        return new EventDateTime()
                .setDateTime(dateTime)
                .setTimeZone(TIMEZONE);
    }

    @Override
    public LocalDateTime fromEventDateTime(EventDateTime eventDateTime) {
        DateTime dateTime = eventDateTime.getDateTime();
        long value = dateTime.getValue();
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(value), ZoneId.of(eventDateTime.getTimeZone()));
    }
}
