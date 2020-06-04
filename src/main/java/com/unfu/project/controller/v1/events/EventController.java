package com.unfu.project.controller.v1.events;

import com.unfu.project.service.events.EventService;
import com.unfu.project.service.events.payload.request.EventWithAttendeesRequest;
import com.unfu.project.service.events.payload.response.EventWithAttendees;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/events")
@AllArgsConstructor
public class EventController {

    private final EventService eventService;

    @GetMapping("/current")
    public ResponseEntity<?> getAllEventsByCurrentMonth() throws IOException {
        var response = eventService.getAllSingleEventsInCurrentMonth();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody EventWithAttendeesRequest request) throws IOException {
        var event = eventService.create(request);
        return ResponseEntity.ok(event);
    }
}
