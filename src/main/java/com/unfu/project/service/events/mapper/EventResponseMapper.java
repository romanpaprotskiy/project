package com.unfu.project.service.events.mapper;

import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import com.unfu.project.service.events.EventDateTimeService;
import com.unfu.project.service.events.payload.response.EventResponse;
import lombok.AllArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public abstract class EventResponseMapper {

    @Autowired
    private EventDateTimeService eventDateTimeService;

    @Mapping(source = "summary", target = "title")
    @Mapping(source = "start", target = "startDate", qualifiedByName = "fromEventDateTime")
    @Mapping(source = "end", target = "endDate", qualifiedByName = "fromEventDateTime")
    public abstract EventResponse map(Event event);

    @Named("fromEventDateTime")
    protected LocalDateTime fromEventDateTime(EventDateTime eventDateTime) {
        return eventDateTimeService.fromEventDateTime(eventDateTime);
    }
}
