package com.unfu.project.service.events.payload.request;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
public class EventWithAttendeesRequest {

    private String title;

    private LocalDate date;

    private LocalTime startTime;

    private LocalTime endTime;

    private String location;

    private List<String> attendees;
}
