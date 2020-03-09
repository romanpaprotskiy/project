package com.unfu.project.service.managerment.mapper;

import com.unfu.project.domain.management.Group;
import com.unfu.project.service.managerment.payload.request.GroupCreateRequest;
import com.unfu.project.service.managerment.payload.response.GroupResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Objects;

@Mapper(componentModel = "spring")
public interface GroupMapper {

    GroupResponse map(Group group);

    @Mapping(source = "parentGroupId", target = "parent")
    Group map(GroupCreateRequest request);

    default Group fromId(Long id) {
        if (Objects.isNull(id)) return null;
        return Group.fromId(id);
    }
}
