package com.unfu.project.controller.v1.schedule;

import com.unfu.project.service.schedule.SubjectScheduleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
