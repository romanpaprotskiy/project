package com.unfu.project.service.users.mapper;

import com.unfu.project.domain.users.Teacher;
import com.unfu.project.service.users.payload.response.PublicTeacherResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Mapper(componentModel = "spring", uses = {ScienceTitleMapper.class})
public interface TeacherMapper {

    @Mapping(source = "user.firstName", target = "firstName")
    @Mapping(source = "user.lastName", target = "lastName")
    @Mapping(source = "scienceTitle.name", target = "scienceTitleName")
    PublicTeacherResponse map(Teacher teacher);

    List<PublicTeacherResponse> map(Collection<Teacher> teachers);

    default Teacher fromId(Long id) {
        if (Objects.isNull(id)) return null;
        Teacher teacher = new Teacher();
        teacher.setId(id);
        return teacher;
    }
}
