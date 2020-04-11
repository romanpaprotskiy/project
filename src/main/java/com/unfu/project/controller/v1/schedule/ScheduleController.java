package com.unfu.project.controller.v1.schedule;

import com.unfu.project.service.schedule.SubjectScheduleService;
import com.unfu.project.service.schedule.payload.request.CreateScheduleRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/api/v1/schedule")
@AllArgsConstructor
public class ScheduleController {

    private final SubjectScheduleService subjectScheduleService;

    @GetMapping("/subject/{id}")
    public ResponseEntity<?> getSubjectScheduleWithDetails(@PathVariable("id") Long subjectId) {
        var response = subjectScheduleService.getWithSubSubjectsBySubjectId(subjectId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/subject")
    public ResponseEntity<?> createSchedule(@Valid @RequestBody CreateScheduleRequest request) throws IOException {
        var response = subjectScheduleService.create(request);
        return ResponseEntity.ok(response);
    }

}
