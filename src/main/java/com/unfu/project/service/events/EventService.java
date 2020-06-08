package com.unfu.project.service.events;

import com.google.api.services.calendar.model.Event;
import com.unfu.project.service.events.payload.request.EventWithAttendeesRequest;
import com.unfu.project.service.events.payload.response.EventWithAttendees;

import java.io.IOException;
import java.util.List;

public interface EventService {

    List<EventWithAttendees> getAllSingleEventsInCurrentMonth() throws IOException;

    EventWithAttendees create(EventWithAttendeesRequest request) throws IOException;

    EventWithAttendees valueOf(Event event);

    EventWithAttendees delete(String eventId) throws IOException;
}
