package com.unfu.project.service.users.mapper;

import com.unfu.project.domain.users.Student;
import com.unfu.project.service.managerment.mapper.GroupMapper;
import com.unfu.project.service.users.payload.response.StudentUserResponse;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring", uses = {GroupMapper.class, UserMapper.class})
public interface StudentUserMapper {

    StudentUserResponse map(Student student);

    List<StudentUserResponse> map(List<Student> student);

    Set<StudentUserResponse> map(Set<Student> students);
}
