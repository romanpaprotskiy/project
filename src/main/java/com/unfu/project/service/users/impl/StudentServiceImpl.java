package com.unfu.project.service.users.impl;

import com.unfu.project.domain.users.Student;
import com.unfu.project.repository.users.StudentRepository;
import com.unfu.project.service.managerment.payload.GroupStudentsCount;
import com.unfu.project.service.users.StudentService;
import com.unfu.project.service.users.mapper.StudentMapper;
import com.unfu.project.service.users.mapper.StudentUserMapper;
import com.unfu.project.service.users.payload.request.CreateStudentRequest;
import com.unfu.project.service.users.payload.response.StudentResponse;
import com.unfu.project.service.users.payload.response.StudentUserResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    private final StudentMapper studentMapper;

    private final StudentUserMapper studentUserMapper;

    @Override
    public Optional<StudentResponse> findResponseByUserId(Long userId) {
         return studentRepository.findByUserId(userId)
                .map(studentMapper::map);
    }

    @Override
    public Page<StudentUserResponse> findAll(Pageable pageable) {
        return studentRepository.findAll(pageable)
                .map(studentUserMapper::map);
    }

    @Override
    public Map<Long, Long> countOfStudentsByGroupIds() {
        var groupStudentsCounts = studentRepository.countStudentsByGroupIdIn();
        Map<Long, Long> map = new HashMap<>();
        for (GroupStudentsCount group : groupStudentsCounts) {
            map.put(group.getGroupId(), group.getCountOfStudents());
        }
        return map;
    }

    @Override
    public StudentResponse createStudent(CreateStudentRequest request) {
        Student student = studentMapper.map(request);
        return studentMapper.map(studentRepository.save(student));
    }
}
