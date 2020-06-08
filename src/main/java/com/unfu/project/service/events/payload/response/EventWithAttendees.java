package com.unfu.project.service.events.payload.response;

import com.google.api.services.calendar.model.Event;
import com.unfu.project.service.users.payload.response.PublicUserResponse;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class EventWithAttendees {

    private String eventId;

    private final String title;

    private final LocalDateTime startDate;

    private final LocalDateTime endDate;

    private final String location;

    private final List<PublicUserResponse> attendees;
}
