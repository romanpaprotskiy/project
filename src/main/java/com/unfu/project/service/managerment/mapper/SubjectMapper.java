package com.unfu.project.service.managerment.mapper;

import com.unfu.project.domain.management.Subject;
import com.unfu.project.service.managerment.payload.response.SubjectResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SubjectMapper {

    SubjectResponse map(Subject subject);
}
