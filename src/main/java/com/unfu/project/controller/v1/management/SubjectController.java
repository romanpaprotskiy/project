package com.unfu.project.controller.v1.management;

import com.unfu.project.service.managerment.SubjectService;
import com.unfu.project.service.managerment.payload.request.CreateSubjectRequest;
import com.unfu.project.service.util.PaginationUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/subjects")
@AllArgsConstructor
public class SubjectController {

    private final SubjectService subjectService;

    @GetMapping
    public ResponseEntity<?> getAll(Pageable pageable) {
        var page = subjectService.findAll(pageable);
        return ResponseEntity.ok(PaginationUtil.valueOf(page));
    }

    @GetMapping("/list")
    public ResponseEntity<?> getAllList() {
        var response = subjectService.findAll();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody CreateSubjectRequest request) {
        var response = subjectService.save(request);
        return ResponseEntity.ok(response);
    }
}
