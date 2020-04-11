package com.unfu.project.service.managerment.mapper;

import com.unfu.project.domain.management.Subject;
import com.unfu.project.service.managerment.payload.request.CreateSubjectRequest;
import com.unfu.project.service.managerment.payload.response.SubjectDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SubjectMapper {

    SubjectDTO map(Subject subject);

    Subject map(CreateSubjectRequest request);

    default Subject fromId(Long id) {
        if (id == null) return null;
        Subject subject = new Subject();
        subject.setId(id);
        return subject;
    }
}
