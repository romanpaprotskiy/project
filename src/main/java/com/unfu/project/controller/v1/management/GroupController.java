package com.unfu.project.controller.v1.management;

import com.unfu.project.service.managerment.GroupService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/groups")
@AllArgsConstructor
public class GroupController {

    private final GroupService groupService;

    @GetMapping
    public ResponseEntity<?> findAll(Pageable pageable) {
        var page = groupService.findAllWithSubgroups(pageable);
        return ResponseEntity.ok(page);
    }
}
