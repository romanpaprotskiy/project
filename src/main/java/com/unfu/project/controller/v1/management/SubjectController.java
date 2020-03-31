package com.unfu.project.controller.v1.management;

import com.unfu.project.service.managerment.SubjectService;
import com.unfu.project.service.util.PaginationUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

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

    @GetMapping("/{id}/details")
    public ResponseEntity<?> getDetails(@PathVariable("id") Long subjectId) throws IOException {
        var response = subjectService.getBySubjectId(subjectId);
        return ResponseEntity.ok(response);
    }
}
