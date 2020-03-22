package com.unfu.project.service.users.impl;

import com.unfu.project.domain.users.Teacher;
import com.unfu.project.repository.users.TeacherRepository;
import com.unfu.project.service.users.TeacherService;
import com.unfu.project.service.users.mapper.TeacherMapper;
import com.unfu.project.service.users.payload.response.PublicTeacherResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherMapper teacherMapper;

    private final TeacherRepository teacherRepository;

    @Override
    public List<PublicTeacherResponse> findAll() {
        List<Teacher> all = teacherRepository.findAll();
        return teacherMapper.map(all);
    }
}
