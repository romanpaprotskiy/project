package com.unfu.project.controller.v1.users;

import com.unfu.project.service.users.UserService;
import com.unfu.project.service.users.payload.request.SearchRequest;
import com.unfu.project.service.users.payload.response.PublicUserResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> getListOfAllActive() {
        var allActive = userService.findAllActive();
        return ResponseEntity.ok(allActive);
    }

    @PostMapping("/all/search")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STUDENT', 'TEACHER')")
    public ResponseEntity<?> getListOfAll(@RequestBody SearchRequest request) {
        var allActive = userService.findAll(request.getSearch());
        return ResponseEntity.ok(allActive);
    }

    @GetMapping("/teachers")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STUDENT', 'TEACHER')")
    public ResponseEntity<?> getTeachersUsers() {
        var users = userService.findTeachersUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/students")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STUDENT', 'TEACHER')")
    public ResponseEntity<?> getStudentUsers() {
        var users = userService.findStudentsUser();
        return ResponseEntity.ok(users);
    }

}
