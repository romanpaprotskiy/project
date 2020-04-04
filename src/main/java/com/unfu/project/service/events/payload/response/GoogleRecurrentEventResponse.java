package com.unfu.project.service.events.payload.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Comparator;

@Data
@Builder
public final class GoogleRecurrentEventResponse implements Comparable<GoogleRecurrentEventResponse> {

    private LocalDateTime start;

    private LocalDateTime end;

    @Override
    public int compareTo(GoogleRecurrentEventResponse o) {
        return Comparator.comparing(GoogleRecurrentEventResponse::getStart, Comparator.nullsLast(Comparator.naturalOrder()))
                .thenComparing(GoogleRecurrentEventResponse::getEnd, Comparator.nullsLast(Comparator.naturalOrder()))
                .compare(this, o);
    }
}
