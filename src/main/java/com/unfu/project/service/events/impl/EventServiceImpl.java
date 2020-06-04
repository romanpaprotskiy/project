package com.unfu.project.service.events.impl;

import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventAttendee;
import com.unfu.project.service.events.EventService;
import com.unfu.project.service.events.GoogleEventService;
import com.unfu.project.service.events.payload.request.EventWithAttendeesRequest;
import com.unfu.project.service.events.payload.response.EventResponse;
import com.unfu.project.service.events.payload.response.EventWithAttendees;
import com.unfu.project.service.users.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Year;
import java.time.ZoneId;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EventServiceImpl implements EventService {

    private final GoogleEventService googleEventService;

    private final UserService userService;

    @Override
    public List<EventWithAttendees> getAllSingleEventsInCurrentMonth() throws IOException {
        LocalDate now = LocalDate.now();
        int monthLength = now.getMonth().length(Year.now().isLeap());
        LocalDate start = LocalDate.of(now.getYear(), now.getMonth(), 1);
        LocalDate end = LocalDate.of(now.getYear(), now.getMonth(), monthLength);
        List<Event> events = googleEventService.getEventsByStartEndDate(start, end, true);
        return events.stream()
                .map(this::valueOf)
                .collect(Collectors.toList());
    }

    @Override
    public EventWithAttendees create(EventWithAttendeesRequest request) throws IOException {
        Event event = googleEventService.createSingleEvent(request);
        return valueOf(event);
    }

    @Override
    public EventWithAttendees valueOf(Event event) {
        Set<String> emails = event.getAttendees().stream()
                .map(EventAttendee::getEmail)
                .collect(Collectors.toSet());
        return EventWithAttendees.builder()
                .startDate(Instant.ofEpochMilli(event.getStart().getDateTime().getValue())
                        .atZone(ZoneId.of(event.getStart().getTimeZone())).toLocalDateTime())
                .endDate(Instant.ofEpochMilli(event.getEnd().getDateTime().getValue())
                        .atZone(ZoneId.of(event.getEnd().getTimeZone())).toLocalDateTime())
                .location(event.getLocation())
                .title(event.getSummary())
                .attendees(userService.findAllByEmails(emails))
                .build();
    }
}
