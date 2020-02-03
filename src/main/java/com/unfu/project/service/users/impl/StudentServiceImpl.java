package com.unfu.project.service.users.impl;

import com.unfu.project.repository.users.StudentRepository;
import com.unfu.project.service.users.StudentService;
import com.unfu.project.service.users.mapper.StudentMapper;
import com.unfu.project.service.users.payload.response.StudentResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    private final StudentMapper studentMapper;

    @Override
    public StudentResponse findResponseByUserId(Long userId) {
        return studentRepository.findByUserId(userId)
                .map(studentMapper::map)
                .orElse(null);
    }
}
