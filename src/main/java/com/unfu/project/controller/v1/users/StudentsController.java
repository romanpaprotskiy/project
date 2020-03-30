package com.unfu.project.controller.v1.users;

import com.unfu.project.service.users.StudentService;
import com.unfu.project.service.users.payload.request.CreateStudentRequest;
import com.unfu.project.service.users.payload.response.StudentResponse;
import com.unfu.project.service.util.PaginationUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/students")
@AllArgsConstructor
public class StudentsController {

    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<?> getStudents(Pageable pageable) {
        var page = studentService.findAll(pageable);
        return ResponseEntity.ok(PaginationUtil.valueOf(page));
    }

    @PostMapping
    public ResponseEntity<?> createStudents(@Valid @RequestBody CreateStudentRequest request) {
        var student = studentService.createStudent(request);
        return ResponseEntity.ok(student);
    }
}
