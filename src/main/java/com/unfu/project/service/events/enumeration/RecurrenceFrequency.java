package com.unfu.project.service.events.enumeration;

import lombok.Getter;

@Getter
public enum RecurrenceFrequency {
    DAILY("FREQ=DAILY;"),
    WEEKLY("FREQ=WEEKLY;"),
    MONTHLY("FREQ=MONTHLY;");

    private final String freq;

    RecurrenceFrequency(String freq) {
        this.freq = freq;
    }
}
