package com.unfu.project.service.managerment.impl;

import com.unfu.project.repository.managerment.SubjectRepository;
import com.unfu.project.service.managerment.SubjectService;
import com.unfu.project.service.managerment.payload.response.SubjectResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;

    @Override
    public List<SubjectResponse> getStudentSubjectsByStudentId(Long studentId) {
        return subjectRepository.findAllByStudentId(studentId)
                .stream()
                .map(s -> SubjectResponse.builder().id(s.getId()).name(s.getName()).build())
                .collect(Collectors.toList());
    }
}
