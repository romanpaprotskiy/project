package com.unfu.project.controller.v1.users;

import com.unfu.project.service.users.TeacherService;
import com.unfu.project.service.users.payload.response.PublicTeacherResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/teacher")
@AllArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        List<PublicTeacherResponse> all = teacherService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(all);
    }
}
