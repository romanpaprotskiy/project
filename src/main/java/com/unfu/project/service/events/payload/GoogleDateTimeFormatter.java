package com.unfu.project.service.events.payload;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.EventDateTime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class GoogleDateTimeFormatter {

    private GoogleDateTimeFormatter() {}

    private final static String DATE_TIME_PATTERN = "yyyy-MM-dd'T'HH:mm:ss";

    private final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);

    public static DateTime format(LocalDate localDate) {
        String format = formatter.format(localDate);
        return DateTime.parseRfc3339(format);
    }

    public static DateTime format(LocalDateTime localDate) {
        String format = formatter.format(localDate);
        return DateTime.parseRfc3339(format);
    }
}
