package com.unfu.project.service.events.payload;

import com.google.api.client.util.DateTime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public final class GoogleDateTimeFormatter {

    private GoogleDateTimeFormatter() {
    }

    private final static String DATE_TIME_PATTERN = "yyyy-MM-dd'T'HH:mm:ssXXX";

    private final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);

    public static DateTime format(LocalDate localDate, String zoneId) {
        String format = formatter.format(localDate.atStartOfDay(ZoneId.of(zoneId)));
        return DateTime.parseRfc3339(format);
    }

    public static DateTime format(LocalDateTime localDate, String zoneId) {
        String format = formatter.format(localDate.atZone(ZoneId.of(zoneId)));
        return DateTime.parseRfc3339(format);
    }
}
