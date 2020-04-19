package com.unfu.project.service.events;

import com.google.api.services.calendar.model.Event;
import com.unfu.project.service.events.payload.request.RecurrentEvent;
import com.unfu.project.service.events.payload.response.GoogleRecurrentEventResponse;

import java.io.IOException;
import java.util.List;

public interface EventService {

    List<Event> getEvents() throws IOException;

    GoogleRecurrentEventResponse getRecurrentEventByEventId(String eventId);

    Event createRecurrentEvent(RecurrentEvent eventRequest) throws IOException;

    Event updateRecurrentEvent(RecurrentEvent eventRequest) throws IOException;

    Event cancelEvent(String eventId) throws IOException;
}
