package com.unfu.project.service.managerment.mapper;

import com.unfu.project.domain.management.Group;
import com.unfu.project.service.managerment.payload.response.GroupWithSubgroupsResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {GroupMapper.class})
public interface GroupWithSubgroupsResponseMapper {

    GroupWithSubgroupsResponse map(Group group);
}
