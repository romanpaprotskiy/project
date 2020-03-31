package com.unfu.project.service.events.impl;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.Events;
import com.unfu.project.service.authentication.data.GoogleDataStore;
import com.unfu.project.service.authentication.util.SecurityUtils;
import com.unfu.project.service.events.EventService;
import com.unfu.project.service.events.payload.response.GoogleRecurrentEventResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
@AllArgsConstructor
public class EventServiceImpl implements EventService {

    private final GoogleDataStore googleDataStore;

    private final NetHttpTransport HTTP_TRANSPORT = new NetHttpTransport();

    private final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    public List<Event> getEvents() throws IOException {
        Calendar calendar = getCalendar();

        Events events = calendar.events()
                .list("primary")
                .execute();

        return events.getItems();
    }

    @Override
    public GoogleRecurrentEventResponse getRecurrentEventByEventId(String eventId) throws IOException {
        Calendar calendar = getCalendar();
        Event event = calendar.events().get("primary", eventId).execute();
        EventDateTime start = event.getStart();
        EventDateTime end = event.getEnd();
        LocalDateTime startDate = Instant.ofEpochMilli(start.getDateTime().getValue())
                .atZone(ZoneId.of(start.getTimeZone()))
                .toLocalDateTime();

        LocalDateTime endDate = Instant.ofEpochMilli(end.getDateTime().getValue())
                .atZone(ZoneId.of(end.getTimeZone()))
                .toLocalDateTime();

        return GoogleRecurrentEventResponse.builder().start(startDate).end(endDate).build();
    }

    private Calendar getCalendar() throws IOException {
        String email = SecurityUtils.getCurrentUserEmail();
        GoogleCredential googleCredential = googleDataStore.get(email);
        return new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, googleCredential)
                .setApplicationName("Project")
                .build();
    }

}
