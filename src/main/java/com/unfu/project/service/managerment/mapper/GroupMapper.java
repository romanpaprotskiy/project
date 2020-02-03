package com.unfu.project.service.managerment.mapper;

import com.unfu.project.domain.management.Group;
import com.unfu.project.service.managerment.payload.response.GroupResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GroupMapper {

    GroupResponse map(Group group);
}
