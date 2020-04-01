package com.unfu.project.service.managerment.mapper;

import com.unfu.project.domain.management.Group;
import com.unfu.project.service.managerment.payload.request.GroupCreateRequest;
import com.unfu.project.service.managerment.payload.response.GroupResponse;
import com.unfu.project.service.managerment.payload.response.GroupWithSubgroupsResponse;
import com.unfu.project.service.users.mapper.TeacherMapper;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.Objects;

@Mapper(componentModel = "spring", uses = {TeacherMapper.class, SubGroupMapper.class})
public interface GroupMapper {

    GroupResponse map(Group group);

    @Mapping(source = "teacherId", target = "guide")
    Group map(GroupCreateRequest request);

    GroupWithSubgroupsResponse mapWithSubGroups(Group group);

    @AfterMapping
    default void after(@MappingTarget GroupWithSubgroupsResponse response, Group group) {
        int size = group.getStudents().size();
        response.setCountOfStudents((long) size);
    }

    default Group fromId(Long id) {
        if (Objects.isNull(id)) return null;
        return Group.fromId(id);
    }
}
