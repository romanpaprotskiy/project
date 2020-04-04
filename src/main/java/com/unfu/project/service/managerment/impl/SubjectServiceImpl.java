package com.unfu.project.service.managerment.impl;

import com.unfu.project.domain.management.Group;
import com.unfu.project.domain.management.Subject;
import com.unfu.project.domain.schedule.SubjectSchedule;
import com.unfu.project.repository.managerment.SubjectRepository;
import com.unfu.project.service.events.EventService;
import com.unfu.project.service.managerment.SubjectService;
import com.unfu.project.service.managerment.mapper.SubjectMapper;
import com.unfu.project.service.managerment.payload.request.CreateSubjectRequest;
import com.unfu.project.service.managerment.payload.response.GroupWithStudents;
import com.unfu.project.service.managerment.payload.response.SubjectResponse;
import com.unfu.project.service.users.mapper.StudentUserMapper;
import com.unfu.project.service.users.mapper.TeacherMapper;
import com.unfu.project.service.users.payload.response.StudentUserResponse;
import com.unfu.project.service.users.payload.response.TeacherResponse;
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

    private final EventService eventService;

    private final StudentUserMapper studentUserMapper;

    @Override
    public List<SubjectResponse> getStudentSubjectsByStudentId(@Nullable Long studentId) {
        if (Objects.isNull(studentId)) return Collections.emptyList();
        return subjectRepository.findAllByStudentId(studentId)
                .stream()
                .map(subjectMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public Page<SubjectResponse> findAll(Pageable pageable) {
        return subjectRepository.findAll(pageable)
                .map(subjectMapper::map);
    }

    @Override
    public SubjectResponse save(CreateSubjectRequest request) {
        Subject subject = subjectMapper.map(request);
        return subjectMapper.map(subjectRepository.save(subject));
    }

    private List<GroupWithStudents> mapGroups(Set<SubjectSchedule> subjectSchedules) {
        if (subjectSchedules.isEmpty()) return Collections.emptyList();
        List<GroupWithStudents> result = new ArrayList<>();
        for (SubjectSchedule schedule : subjectSchedules) {
            if (schedule.getActive()) {
//                String eventId = schedule.getGoogleEvent().getEventId();
//                GoogleRecurrentEventResponse scheduleResponse =
//                        eventService.getRecurrentEventByEventId(eventId);
                List<StudentUserResponse> studentResponses = mapStudents(schedule.getGroup());
                GroupWithStudents groupWithStudents = GroupWithStudents.builder()
                        .id(schedule.getGroup().getId())
                        .name(schedule.getGroup().getName())
                        .students(studentResponses)
                        .build();
                result.add(groupWithStudents);
            }
        }
        return result;
    }

    private List<StudentUserResponse> mapStudents(Group group) {
        return group.getStudents().stream()
                .map(studentUserMapper::map)
                .collect(Collectors.toList());
    }

    private List<TeacherResponse> mapTeachers(Set<SubjectSchedule> subjectSchedules) {
        if (subjectSchedules.isEmpty()) return Collections.emptyList();
        return subjectSchedules.stream()
                .filter(SubjectSchedule::getActive)
                .map(SubjectSchedule::getTeacher)
                .map(teacherMapper::mapResponse)
                .collect(Collectors.toList());
    }
}
