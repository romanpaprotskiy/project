package com.unfu.project.service.schedule.impl;

import com.unfu.project.repository.schedule.SubjectScheduleRepository;
import com.unfu.project.service.managerment.mapper.GroupMapper;
import com.unfu.project.service.schedule.SubjectScheduleService;
import com.unfu.project.service.schedule.payload.response.SubjectWithSubSubjectsAndSchedules;
import com.unfu.project.service.users.mapper.TeacherMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SubjectScheduleServiceImpl implements SubjectScheduleService {

    private final SubjectScheduleRepository subjectScheduleRepository;

    private final GroupMapper groupMapper;

    private final TeacherMapper teacherMapper;

    @Override
    public List<SubjectWithSubSubjectsAndSchedules> getWithSubSubjectsBySubjectId(Long subjectId) {
        return subjectScheduleRepository.findAllBySubjectId(subjectId)
                .stream()
                .map(s -> SubjectWithSubSubjectsAndSchedules.builder()
                        .scheduleId(s.getId())
                        .group(groupMapper.mapWithStudents(s.getGroup()))
                        .teacher(teacherMapper.mapResponse(s.getTeacher()))
                        .build())
                .sorted()
                .collect(Collectors.toList());
    }
}
