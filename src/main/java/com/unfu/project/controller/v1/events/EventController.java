package com.unfu.project.controller.v1.events;

import com.unfu.project.service.events.EventService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/events")
@AllArgsConstructor
public class EventController {

    private final EventService eventService;

    @GetMapping("/current")
    public ResponseEntity<?> getCurrencyUserEvents() throws IOException {
        var events = eventService.getEvents();
        return ResponseEntity.ok(events);
    }
}
