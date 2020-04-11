package com.unfu.project.service.users.mapper;

import com.unfu.project.domain.users.Student;
import com.unfu.project.service.managerment.mapper.GroupMapper;
import com.unfu.project.service.managerment.mapper.SubGroupMapper;
import com.unfu.project.service.users.payload.request.CreateStudentRequest;
import com.unfu.project.service.users.payload.response.StudentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {GroupMapper.class, UserMapper.class, SubGroupMapper.class})
public interface StudentMapper {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.email", target = "userEmail")
    StudentDTO map(Student student);

    @Mapping(source = "userId", target = "user")
    @Mapping(source = "groupId", target = "group")
    Student map(CreateStudentRequest request);
}
