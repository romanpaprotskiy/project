package com.unfu.project.service.users.impl;

import com.unfu.project.repository.users.StudentRepository;
import com.unfu.project.service.users.StudentService;
import com.unfu.project.service.users.mapper.StudentMapper;
import com.unfu.project.service.users.mapper.StudentUserMapper;
import com.unfu.project.service.users.payload.response.StudentResponse;
import com.unfu.project.service.users.payload.response.StudentUserResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    private final StudentMapper studentMapper;

    private final StudentUserMapper studentUserMapper;

    @Override
    public StudentResponse findResponseByUserId(Long userId) {
        return studentRepository.findByUserId(userId)
                .map(studentMapper::map)
                .orElse(null);
    }

    @Override
    public Page<StudentUserResponse> findAll(Pageable pageable) {
        return studentRepository.findAll(pageable)
                .map(studentUserMapper::map);
    }
}
