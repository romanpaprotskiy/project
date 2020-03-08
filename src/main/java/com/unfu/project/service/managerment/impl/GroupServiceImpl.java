package com.unfu.project.service.managerment.impl;

import com.unfu.project.domain.management.Group;
import com.unfu.project.repository.managerment.GroupRepository;
import com.unfu.project.service.managerment.GroupService;
import com.unfu.project.service.managerment.mapper.GroupMapper;
import com.unfu.project.service.managerment.payload.response.GroupResponse;
import com.unfu.project.service.managerment.payload.response.GroupWithSubgroupsResponse;
import com.unfu.project.service.users.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;

    private final GroupMapper groupMapper;

    private final StudentService studentService;

    private Map<Long, Long> countOfStudentsMap;

    @Override
    public Page<GroupResponse> findAll(Pageable pageable) {
        return groupRepository.findAll(pageable)
                .map(groupMapper::map);
    }

    @Override
    public Page<GroupWithSubgroupsResponse> findAllWithSubgroups(Pageable pageable) {
        var groups = groupRepository.findAllByParentIsNull(pageable);
        Map<Group, List<Group>> subGroupsMap = groups.stream()
                .flatMap(v -> v.getSubGroups().stream())
                .collect(Collectors.groupingBy(Group::getParent));
        countOfStudentsMap = studentService.countOfStudentsByGroupIds();
        var responses = subGroupsMap.keySet().stream()
                .map(this::mapGroupWithSubgroupsResponse)
                .collect(Collectors.toList());
        return new PageImpl<>(responses, pageable, responses.size());
    }

    private GroupWithSubgroupsResponse mapGroupWithSubgroupsResponse(Group data) {
        var group = GroupWithSubgroupsResponse.builder()
                .id(data.getId())
                .name(data.getName())
                .subGroups(mapSubGroups(data.getSubGroups()))
                .build();
        group.setCountOfStudents(countOfStudentsBySubGroups(group.getSubGroups()));
        return group;
    }

    private List<GroupWithSubgroupsResponse> mapSubGroups(Collection<Group> groups) {
        return groups.stream()
                .map(v -> GroupWithSubgroupsResponse.builder()
                        .id(v.getId())
                        .name(v.getName())
                        .countOfStudents(countOfStudentsMap.get(v.getId()))
                        .build())
                .collect(Collectors.toList());
    }

    private Long countOfStudentsBySubGroups(List<GroupWithSubgroupsResponse> subgroups) {
        return subgroups.stream()
                .map(GroupWithSubgroupsResponse::getCountOfStudents)
                .reduce(0L, Long::sum);
    }
}
