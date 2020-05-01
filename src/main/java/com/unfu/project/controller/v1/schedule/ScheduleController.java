package com.unfu.project.controller.v1.schedule;

import com.unfu.project.service.schedule.SubjectScheduleService;
import com.unfu.project.service.schedule.payload.request.ScheduleCreateRequest;
import com.unfu.project.service.schedule.payload.request.ScheduleUpdateRequest;
import com.unfu.project.service.schedule.payload.response.SubjectEventDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/schedule")
@AllArgsConstructor
public class ScheduleController {

    private final SubjectScheduleService subjectScheduleService;

    @GetMapping("/subject/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'TEACHER')")
    public ResponseEntity<?> getSubjectScheduleWithDetails(@PathVariable("id") Long subjectId) {
        var response = subjectScheduleService.getWithSubSubjectsBySubjectId(subjectId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/month/current")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'TEACHER', 'STUDENT')")
    public ResponseEntity<?> getAllSchedulesByCurrentMonth() throws IOException {
        var response = subjectScheduleService.getAllByCurrentMonth();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/subject")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> createSchedule(@Valid @RequestBody ScheduleCreateRequest request) throws IOException {
        var response = subjectScheduleService.create(request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/subject")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> updateSchedule(@Valid @RequestBody ScheduleUpdateRequest request) throws IOException {
        var response = subjectScheduleService.update(request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}/subject")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> deleteSubjectSchedule(@PathVariable("id") Long subjectScheduleId) throws IOException {
        var response = subjectScheduleService.deleteSubjectScheduleById(subjectScheduleId);
        return ResponseEntity.ok(response);
    }

}
