package com.unfu.project.service.events.impl;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.Events;
import com.unfu.project.service.authentication.data.GoogleDataStore;
import com.unfu.project.service.authentication.util.SecurityUtils;
import com.unfu.project.service.events.EventBuilderService;
import com.unfu.project.service.events.EventService;
import com.unfu.project.service.events.payload.Recurrence;
import com.unfu.project.service.events.payload.request.RecurrentEvent;
import com.unfu.project.service.events.payload.response.GoogleRecurrentEventResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.*;
import java.util.List;

@Service
@AllArgsConstructor
public class EventServiceImpl implements EventService {

    private final GoogleDataStore googleDataStore;

    private final NetHttpTransport HTTP_TRANSPORT = new NetHttpTransport();

    private final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    private final EventBuilderService eventBuilderService;

    public List<Event> getEvents() throws IOException {
        Calendar calendar = getCalendar();
        Events events = calendar.events()
                .list("primary")
                .execute();
        return events.getItems();
    }

    @Override
    public GoogleRecurrentEventResponse getRecurrentEventByEventId(String eventId) {
        try {
            Calendar calendar = getCalendar();
            Event event = calendar.events().get("primary", eventId).execute();
            EventDateTime start = event.getStart();
            EventDateTime end = event.getEnd();
            LocalDate startDate = Instant.ofEpochMilli(start.getDateTime().getValue())
                    .atZone(ZoneId.of(start.getTimeZone()))
                    .toLocalDate();
            LocalTime startTime = Instant.ofEpochMilli(start.getDateTime().getValue())
                    .atZone(ZoneId.of(start.getTimeZone()))
                    .toLocalTime();
            LocalTime endTime = Instant.ofEpochMilli(end.getDateTime().getValue())
                    .atZone(ZoneId.of(end.getTimeZone()))
                    .toLocalTime();
            return GoogleRecurrentEventResponse.builder()
                    .startDate(startDate)
                    .startTime(startTime)
                    .endTime(endTime)
                    .build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Event createRecurrentEvent(RecurrentEvent eventRequest) throws IOException {
        Recurrence recurrence = getRecurrence(eventRequest);
        Event event = eventBuilderService
                .summary(eventRequest.getSummary())
                .recurrence(recurrence)
                .attendees(eventRequest.getEmails())
                .startDateTime(eventRequest.getStartDate())
                .endDateTime(eventRequest.getEndDate())
                .build();
        Calendar calendar = getCalendar();
        return calendar.events().insert("primary", event).execute();
    }

    @Override
    public Event updateRecurrentEvent(RecurrentEvent eventRequest) throws IOException {
        Recurrence recurrence = getRecurrence(eventRequest);
        Calendar calendar = getCalendar();
        Event event = calendar.events().get("primary", eventRequest.getEventId()).execute();
        Event newEvent = eventBuilderService
                .summary(eventRequest.getSummary())
                .recurrence(recurrence)
                .attendees(eventRequest.getEmails())
                .startDateTime(eventRequest.getStartDate())
                .endDateTime(eventRequest.getEndDate())
                .build();
        return calendar.events().update("primary", event.getId(), newEvent).execute();
    }

    @Override
    public Event cancelEvent(String eventId) throws IOException {
        Calendar calendar = getCalendar();
        Event event = calendar.events().get("primary", eventId).execute();
        event.setStatus("cancelled");
        return calendar.events().update("primary",eventId,event).execute();
    }

    private Recurrence getRecurrence(RecurrentEvent eventRequest) {
        return Recurrence.builder()
                .frequency(eventRequest.getFrequency())
                .count(eventRequest.getCount())
                .interval(eventRequest.getInterval())
                .endDate(eventRequest.getEndDateOfEvents())
                .build();
    }

    private Calendar getCalendar() throws IOException {
        String email = SecurityUtils.getCurrentUserEmail();
        GoogleCredential googleCredential = googleDataStore.get(email);
        return new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, googleCredential)
                .setApplicationName("Project")
                .build();
    }

}
