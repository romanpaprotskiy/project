package com.unfu.project.service.events.payload.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public final class GoogleRecurrentEventResponse {

    private LocalDateTime start;

    private LocalDateTime end;
}
