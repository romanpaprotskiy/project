package com.unfu.project.service.events;

import com.google.api.services.calendar.model.Event;
import com.unfu.project.service.events.payload.request.CreateRecurrentEvent;
import com.unfu.project.service.events.payload.response.GoogleRecurrentEventResponse;

import java.io.IOException;
import java.util.List;

public interface EventService {

    List<Event> getEvents() throws IOException;

    GoogleRecurrentEventResponse getRecurrentEventByEventId(String eventId);

    Event createRecurrentEvent(CreateRecurrentEvent eventRequest) throws IOException;
}
