package com.unfu.project.service.events.payload.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EventResponse {

    private String title;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private String location;
}
