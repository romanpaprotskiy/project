package com.unfu.project.service.managerment.impl;

import com.unfu.project.domain.management.SubGroup;
import com.unfu.project.repository.managerment.GroupRepository;
import com.unfu.project.repository.managerment.SubGroupRepository;
import com.unfu.project.service.managerment.GroupService;
import com.unfu.project.service.managerment.mapper.GroupMapper;
import com.unfu.project.service.managerment.mapper.SubGroupMapper;
import com.unfu.project.service.managerment.payload.request.GroupCreateRequest;
import com.unfu.project.service.managerment.payload.request.SubGroupCreateRequest;
import com.unfu.project.service.managerment.payload.response.GroupResponse;
import com.unfu.project.service.managerment.payload.response.GroupWithSubgroupsResponse;
import com.unfu.project.service.managerment.payload.response.SubGroupResponse;
import com.unfu.project.service.users.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;

    private final GroupMapper groupMapper;

    private final StudentService studentService;

    private final SubGroupRepository subGroupRepository;

    private final SubGroupMapper subGroupMapper;

    @Override
    public Page<GroupResponse> findAll(Pageable pageable) {
        return groupRepository.findAll(pageable)
                .map(groupMapper::map);
    }

    @Override
    public Page<GroupWithSubgroupsResponse> findAllWithSubgroups(Pageable pageable) {
        return groupRepository.findAllAndFetch(pageable)
                .map(groupMapper::mapWithSubGroups);
    }

    @Override
    public GroupResponse save(GroupCreateRequest request) {
        var group = groupMapper.map(request);
        var saved = groupRepository.save(group);
        return groupMapper.map(saved);
    }

    @Override
    public SubGroupResponse save(SubGroupCreateRequest request) {
        SubGroup subGroup = subGroupMapper.map(request);
        return subGroupMapper.map(subGroupRepository.save(subGroup));
    }

    @Override
    public List<GroupResponse> findAllWithParentNull() {
        return groupRepository.findAll()
                .stream()
                .map(groupMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public List<SubGroupResponse> getSubgroupsByGroup(Long id) {
        return subGroupRepository.findAllByGroupId(id)
                .stream()
                .map(subGroupMapper::map)
                .collect(Collectors.toList());
    }
}
