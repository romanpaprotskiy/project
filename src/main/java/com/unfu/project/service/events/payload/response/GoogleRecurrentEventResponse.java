package com.unfu.project.service.events.payload.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Comparator;

@Data
@Builder
public final class GoogleRecurrentEventResponse implements Comparable<GoogleRecurrentEventResponse> {

    private final LocalDate startDate;

    private final LocalTime startTime;

    private final LocalTime endTime;

    @Override
    public int compareTo(GoogleRecurrentEventResponse o) {
        return Comparator.comparing(GoogleRecurrentEventResponse::getStartDate, Comparator.nullsLast(Comparator.naturalOrder()))
                .thenComparing(GoogleRecurrentEventResponse::getStartTime, Comparator.nullsLast(Comparator.naturalOrder()))
                .thenComparing(GoogleRecurrentEventResponse::getEndTime, Comparator.nullsLast(Comparator.naturalOrder()))
                .compare(this, o);
    }
}
