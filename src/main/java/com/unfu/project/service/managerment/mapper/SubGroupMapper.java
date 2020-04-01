package com.unfu.project.service.managerment.mapper;

import com.unfu.project.domain.management.SubGroup;
import com.unfu.project.service.managerment.payload.request.SubGroupCreateRequest;
import com.unfu.project.service.managerment.payload.response.SubGroupResponse;
import com.unfu.project.service.managerment.payload.response.SubGroupWithCountOfStudents;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.Collection;

@Mapper(componentModel = "spring", uses = {GroupMapper.class})
public interface SubGroupMapper {

    SubGroupResponse map(SubGroup subGroup);

    @Mapping(source = "groupId", target = "group")
    SubGroup map(SubGroupCreateRequest request);

    SubGroupWithCountOfStudents mapWithCountOfStudents(SubGroup subGroup);

    Collection<SubGroup> mapWithCountOfStudents(Collection<SubGroup> subGroup);

    @AfterMapping
    default void after(@MappingTarget SubGroupWithCountOfStudents response, SubGroup group) {
        int size = group.getStudents().size();
        response.setCountOfStudents((long) size);
    }
}
