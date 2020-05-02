package com.unfu.project.service.managerment.impl;

import com.unfu.project.domain.management.Subject;
import com.unfu.project.exception.ResourceNotFoundException;
import com.unfu.project.repository.managerment.SubjectRepository;
import com.unfu.project.service.events.GoogleEventService;
import com.unfu.project.service.managerment.SubjectService;
import com.unfu.project.service.managerment.mapper.SubjectMapper;
import com.unfu.project.service.managerment.payload.request.CreateSubjectRequest;
import com.unfu.project.service.managerment.payload.response.SubjectDTO;
import com.unfu.project.service.users.mapper.StudentUserMapper;
import com.unfu.project.service.users.mapper.TeacherMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;

    private final TeacherMapper teacherMapper;

    private final SubjectMapper subjectMapper;

    private final StudentUserMapper studentUserMapper;

    private final GoogleEventService googleEventService;

    @Override
    public List<SubjectDTO> getStudentSubjectsByStudentId(@Nullable Long studentId) {
        if (Objects.isNull(studentId)) return Collections.emptyList();
        return subjectRepository.findAllByStudentId(studentId)
                .stream()
                .map(subjectMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public Page<SubjectDTO> findAll(Pageable pageable) {
        return subjectRepository.findAll(pageable)
                .map(subjectMapper::map);
    }

    @Override
    public List<SubjectDTO> findAll() {
        return subjectRepository.findAll()
                .stream()
                .map(subjectMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public SubjectDTO save(CreateSubjectRequest request) {
        Subject subject = subjectMapper.map(request);
        return subjectMapper.map(subjectRepository.save(subject));
    }

    @Override
    public SubjectDTO findById(Long id) {
        return subjectRepository
                .findById(id).map(subjectMapper::map)
                .orElseThrow(ResourceNotFoundException::new);
    }
}
