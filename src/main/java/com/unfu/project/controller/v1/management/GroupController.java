package com.unfu.project.controller.v1.management;

import com.unfu.project.service.managerment.GroupService;
import com.unfu.project.service.managerment.payload.request.GroupCreateRequest;
import com.unfu.project.service.managerment.payload.request.SubGroupCreateRequest;
import com.unfu.project.service.util.PaginationUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/groups")
@AllArgsConstructor
public class GroupController {

    private final GroupService groupService;

    @GetMapping
    public ResponseEntity<?> findAll(Pageable pageable) {
        var page = groupService.findAllWithSubgroups(pageable);
        return ResponseEntity.ok(PaginationUtil.valueOf(page));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody GroupCreateRequest request) {
        var response = groupService.save(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/subgroup")
    public ResponseEntity<?> save(@RequestBody SubGroupCreateRequest request) {
        var response = groupService.save(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/root")
    public ResponseEntity<?> findAll() {
        var response = groupService.findAllWithParentNull();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/subgroups")
    public ResponseEntity<?> getSubgroups(@PathVariable("id") Long groupId) {
        var response = groupService.getSubgroupsByGroup(groupId);
        return ResponseEntity.ok(response);
    }
}
