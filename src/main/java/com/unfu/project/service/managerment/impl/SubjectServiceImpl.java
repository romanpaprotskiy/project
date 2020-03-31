package com.unfu.project.service.managerment.impl;

import com.unfu.project.domain.management.Group;
import com.unfu.project.domain.management.Subject;
import com.unfu.project.domain.schedule.SubjectSchedule;
import com.unfu.project.exception.BadRequestException;
import com.unfu.project.repository.managerment.SubjectRepository;
import com.unfu.project.service.events.EventService;
import com.unfu.project.service.events.payload.response.GoogleRecurrentEventResponse;
import com.unfu.project.service.managerment.SubjectService;
import com.unfu.project.service.managerment.mapper.GroupMapper;
import com.unfu.project.service.managerment.mapper.SubjectMapper;
import com.unfu.project.service.managerment.payload.response.GroupResponse;
import com.unfu.project.service.managerment.payload.response.GroupWithStudents;
import com.unfu.project.service.managerment.payload.response.SubjectResponse;
import com.unfu.project.service.managerment.payload.response.SubjectWithParticipants;
import com.unfu.project.service.users.mapper.StudentMapper;
import com.unfu.project.service.users.mapper.StudentUserMapper;
import com.unfu.project.service.users.mapper.TeacherMapper;
import com.unfu.project.service.users.payload.response.StudentResponse;
import com.unfu.project.service.users.payload.response.StudentUserResponse;
import com.unfu.project.service.users.payload.response.TeacherResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
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
    public List<SubjectResponse> getStudentSubjectsByStudentId(Long studentId) {
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
    public SubjectWithParticipants getBySubjectId(Long subjectId) throws IOException {
        Subject subject = subjectRepository.findByIdAndFetch(subjectId)
                .orElseThrow(BadRequestException::new);
        Set<SubjectSchedule> subjectSchedules = subject.getSubjectSchedules();
        SubjectResponse subjectResponse = subjectMapper.map(subject);
        List<TeacherResponse> teacherResponses = mapTeachers(subjectSchedules);
        List<GroupWithStudents> groupWithStudents = mapGroups(subjectSchedules);
        return SubjectWithParticipants.builder()
                .subject(subjectResponse)
                .teachers(teacherResponses)
                .groups(groupWithStudents)
                .build();
    }

    private List<GroupWithStudents> mapGroups(Set<SubjectSchedule> subjectSchedules) throws IOException {
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
        List<StudentUserResponse> studentResponses = group.getStudents().stream()
                .map(studentUserMapper::map)
                .collect(Collectors.toList());
        List<StudentUserResponse> fromSubGroups = mapFromSubGroups(group.getSubGroups());
        studentResponses.addAll(fromSubGroups);
        return studentResponses;
    }

    private List<StudentUserResponse> mapFromSubGroups(Collection<Group> groups) {
        return groups.stream()
                .flatMap(g -> g.getStudents().stream())
                .map(studentUserMapper::map)
                .collect(Collectors.toList());
    }

    private List<TeacherResponse> mapTeachers(Set<SubjectSchedule> subjectSchedules) {
        return subjectSchedules.stream()
                .filter(SubjectSchedule::getActive)
                .map(SubjectSchedule::getTeacher)
                .map(teacherMapper::mapResponse)
                .collect(Collectors.toList());
    }
}
