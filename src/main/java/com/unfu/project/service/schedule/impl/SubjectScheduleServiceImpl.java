package com.unfu.project.service.schedule.impl;

import com.google.api.services.calendar.model.Event;
import com.unfu.project.domain.schedule.SubjectSchedule;
import com.unfu.project.repository.schedule.SubjectScheduleRepository;
import com.unfu.project.service.events.EventService;
import com.unfu.project.service.events.enumeration.RecurrenceFrequency;
import com.unfu.project.service.events.payload.request.CreateRecurrentEvent;
import com.unfu.project.service.managerment.SubjectService;
import com.unfu.project.service.managerment.mapper.GroupMapper;
import com.unfu.project.service.schedule.ScheduleDetailsService;
import com.unfu.project.service.schedule.SubjectScheduleService;
import com.unfu.project.service.schedule.mapper.ScheduleMapper;
import com.unfu.project.service.schedule.payload.request.CreateScheduleRequest;
import com.unfu.project.service.schedule.payload.response.SubjectWithSubSubjectsAndSchedules;
import com.unfu.project.service.users.StudentService;
import com.unfu.project.service.users.TeacherService;
import com.unfu.project.service.users.mapper.TeacherMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SubjectScheduleServiceImpl implements SubjectScheduleService {

    private final SubjectScheduleRepository subjectScheduleRepository;

    private final GroupMapper groupMapper;

    private final TeacherMapper teacherMapper;

    private final EventService eventService;

    private final ScheduleMapper scheduleMapper;

    private final ScheduleDetailsService scheduleDetailsService;

    @Override
    public List<SubjectWithSubSubjectsAndSchedules> getWithSubSubjectsBySubjectId(Long subjectId) {
        return subjectScheduleRepository.findAllBySubjectId(subjectId)
                .stream()
                .map(this::fromSubjectSchedule)
                .sorted()
                .collect(Collectors.toList());
    }

    private SubjectWithSubSubjectsAndSchedules fromSubjectSchedule(SubjectSchedule subjectSchedule) {
        return SubjectWithSubSubjectsAndSchedules.builder()
                .scheduleId(subjectSchedule.getId())
                .group(groupMapper.mapWithStudents(subjectSchedule.getGroup()))
                .teacher(teacherMapper.mapResponse(subjectSchedule.getTeacher()))
                .schedule(eventService.getRecurrentEventByEventId(subjectSchedule.getGoogleEvent().getEventId()))
                .build();
    }

    @Override
    @Transactional
    public SubjectWithSubSubjectsAndSchedules create(CreateScheduleRequest request) throws IOException {
        List<String> emails = scheduleDetailsService.getStudentsEmailByGroup(request.getGroupId());
        emails.add(scheduleDetailsService.getTeacherEmail(request.getTeacherId()));
        CreateRecurrentEvent.CreateRecurrentEventBuilder builder = CreateRecurrentEvent.builder()
                .startDate(LocalDateTime.of(request.getStartDate(), request.getStartTime()))
                .endDate(LocalDateTime.of(request.getStartDate(), request.getEndTime()))
                .frequency(RecurrenceFrequency.WEEKLY)
                .interval(request.getInterval())
                .summary(scheduleDetailsService.getSubjectName(request.getSubjectId()))
                .emails(emails);
        switch (request.getRestriction().getRestrictionType()) {
            case COUNT:
                builder.count(request.getRestriction().getCount());
                break;
            case END_DATE:
                builder.endDateOfEvents(request.getRestriction().getEndDate());
                break;
        }
        CreateRecurrentEvent createRecurrentEvent = builder.build();
        Event recurrentEvent = eventService.createRecurrentEvent(createRecurrentEvent);
        SubjectSchedule subjectSchedule = scheduleMapper.mapFromRequest(request, recurrentEvent);
        return fromSubjectSchedule(subjectScheduleRepository.saveAndFlush(subjectSchedule));
    }
}
