package com.unfu.project.service.events.builder;

import com.unfu.project.service.events.enumeration.RecurrenceFrequency;
import com.unfu.project.service.events.payload.GoogleDateTimeFormatter;

import java.time.LocalDate;
import java.util.Objects;

public final class RecurrenceRuleBuilder {

    private final StringBuilder rules = new StringBuilder().append("RRULE:");

    private RecurrenceRuleBuilder() {
    }

    public static RecurrenceRuleBuilder construct() {
        return new RecurrenceRuleBuilder();
    }

    public RecurrenceRuleBuilder frequency(RecurrenceFrequency frequency) {
        rules.append(frequency.getFreq());
        return this;
    }

    public RecurrenceRuleBuilder count(Integer count) {
        if (Objects.nonNull(count)) {
            rules.append("COUNT=").append(count).append(";");
        }
        return this;
    }

    public RecurrenceRuleBuilder until(LocalDate endDate) {
        if (Objects.nonNull(endDate)) {
            String stringDate = GoogleDateTimeFormatter.format(endDate).toString();
            rules.append("UNTIL=").append(stringDate).append(";");
        }
        return this;
    }

    public RecurrenceRuleBuilder interval(Integer interval) {
        if (Objects.nonNull(interval)) {
            rules.append("INTERVAL=").append(interval).append(";");
        }
        return this;
    }

    public String build() {
        return rules.toString();
    }
}
