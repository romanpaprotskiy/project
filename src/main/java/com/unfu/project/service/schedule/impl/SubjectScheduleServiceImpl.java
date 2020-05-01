package com.unfu.project.service.schedule.impl;

import com.google.api.services.calendar.model.Event;
import com.unfu.project.domain.events.GoogleEvent;
import com.unfu.project.domain.schedule.SubjectSchedule;
import com.unfu.project.exception.ResourceNotFoundException;
import com.unfu.project.repository.schedule.SubjectScheduleRepository;
import com.unfu.project.service.events.EventService;
import com.unfu.project.service.events.enumeration.RecurrenceFrequency;
import com.unfu.project.service.events.payload.request.RecurrentEvent;
import com.unfu.project.service.managerment.mapper.GroupMapper;
import com.unfu.project.service.schedule.ScheduleDetailsService;
import com.unfu.project.service.schedule.SubjectScheduleService;
import com.unfu.project.service.schedule.mapper.ScheduleMapper;
import com.unfu.project.service.schedule.payload.request.ScheduleCreateRequest;
import com.unfu.project.service.schedule.payload.request.ScheduleRestrictionRequest;
import com.unfu.project.service.schedule.payload.request.ScheduleUpdateRequest;
import com.unfu.project.service.schedule.payload.response.SubjectEventDTO;
import com.unfu.project.service.schedule.payload.response.SubjectWithSubSubjectsAndSchedules;
import com.unfu.project.service.users.mapper.TeacherMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.*;
import java.util.*;
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
        return subjectScheduleRepository.findAllBySubjectIdAndActive(subjectId, true)
                .stream()
                .map(this::fromSubjectSchedule)
                .sorted()
                .collect(Collectors.toList());
    }

    @Override
    public List<SubjectEventDTO> getAllByCurrentMonth() throws IOException {
        LocalDate now = LocalDate.now();
        int end = now.getMonth().length(Year.isLeap(now.getYear()));
        List<Event> events = eventService.getEventsByStartEndDate(LocalDate.of(now.getYear(), now.getMonth(), 1),
                LocalDate.of(now.getYear(), now.getMonth(), end), true);
        List<SubjectSchedule> schedules = subjectScheduleRepository.findAll();
        return map(schedules, events);
    }

    private List<SubjectEventDTO> map(List<SubjectSchedule> schedules, List<Event> events) {
        schedules.sort(Comparator.comparing(e -> e.getGoogleEvent().getEventId()));
        List<SubjectEventDTO> result = new ArrayList<>();
        for (Event event : events) {
            String recurringEventId = event.getRecurringEventId();
            SubjectSchedule schedule = find(recurringEventId, schedules);
            SubjectEventDTO dto = SubjectEventDTO.builder()
                    .startDateTime(Instant.ofEpochMilli(event.getStart().getDateTime().getValue())
                            .atZone(ZoneId.of(event.getStart().getTimeZone())).toLocalDateTime())
                    .endDateTime(Instant.ofEpochMilli(event.getEnd().getDateTime().getValue())
                            .atZone(ZoneId.of(event.getEnd().getTimeZone())).toLocalDateTime())
                    .subjectId(schedule.getSubject().getId())
                    .subjectName(schedule.getSubject().getName())
                    .groupId(schedule.getGroup().getId())
                    .groupName(schedule.getGroup().getName())
                    .build();
            result.add(dto);
        }
        return result;
    }

    private SubjectSchedule find(String eventId, List<SubjectSchedule> schedules) {
        GoogleEvent googleEvent = new GoogleEvent();
        googleEvent.setEventId(eventId);
        SubjectSchedule subjectSchedule = new SubjectSchedule();
        subjectSchedule.setGoogleEvent(googleEvent);
        int index = Collections.binarySearch(schedules, subjectSchedule,
                Comparator.comparing(e -> e.getGoogleEvent().getEventId()));
        if (index > -1) return schedules.get(index);
        return null;
    }

    private SubjectWithSubSubjectsAndSchedules fromSubjectSchedule(SubjectSchedule subjectSchedule) {
        return SubjectWithSubSubjectsAndSchedules.builder()
                .scheduleId(subjectSchedule.getId())
                .subjectId(subjectSchedule.getSubject().getId())
                .group(groupMapper.mapWithStudents(subjectSchedule.getGroup()))
                .teacher(teacherMapper.mapResponse(subjectSchedule.getTeacher()))
                .schedule(eventService.getRecurrentEventByEventId(subjectSchedule.getGoogleEvent().getEventId()))
                .eventId(subjectSchedule.getGoogleEvent().getEventId())
                .build();
    }

    @Override
    @Transactional
    public SubjectWithSubSubjectsAndSchedules create(ScheduleCreateRequest request) throws IOException {
        List<String> emails = scheduleDetailsService.getStudentsEmailByGroup(request.getGroupId());
        emails.add(scheduleDetailsService.getTeacherEmail(request.getTeacherId()));
        RecurrentEvent.RecurrentEventBuilder builder = RecurrentEvent.builder()
                .startDate(LocalDateTime.of(request.getStartDate(), request.getStartTime()))
                .endDate(LocalDateTime.of(request.getStartDate(), request.getEndTime()))
                .frequency(RecurrenceFrequency.WEEKLY)
                .interval(request.getInterval())
                .summary(scheduleDetailsService.getSubjectName(request.getSubjectId()))
                .emails(emails);
        setRestriction(builder, request.getRestriction());
        RecurrentEvent createRecurrentEvent = builder.build();
        Event recurrentEvent = eventService.createRecurrentEvent(createRecurrentEvent);
        SubjectSchedule subjectSchedule = scheduleMapper.mapFromRequest(request, recurrentEvent);
        return fromSubjectSchedule(subjectScheduleRepository.saveAndFlush(subjectSchedule));
    }

    @Override
    @Transactional
    public SubjectWithSubSubjectsAndSchedules update(ScheduleUpdateRequest request) throws IOException {
        List<String> emails = scheduleDetailsService.getStudentsEmailByGroup(request.getGroupId());
        emails.add(scheduleDetailsService.getTeacherEmail(request.getTeacherId()));
        RecurrentEvent.RecurrentEventBuilder builder = RecurrentEvent.builder()
                .startDate(LocalDateTime.of(request.getStartDate(), request.getStartTime()))
                .endDate(LocalDateTime.of(request.getStartDate(), request.getEndTime()))
                .frequency(RecurrenceFrequency.WEEKLY)
                .interval(request.getInterval())
                .eventId(request.getEventId())
                .summary(scheduleDetailsService.getSubjectName(request.getSubjectId()));
        setRestriction(builder, request.getRestriction());
        RecurrentEvent recurrentEvent = builder.build();
        Event event = eventService.updateRecurrentEvent(recurrentEvent);
        SubjectSchedule subjectSchedule = scheduleMapper.mapFromRequest(request, event, request.getGoogleEventId());
        return fromSubjectSchedule(subjectScheduleRepository.saveAndFlush(subjectSchedule));
    }

    private void setRestriction(RecurrentEvent.RecurrentEventBuilder builder, ScheduleRestrictionRequest restriction) {
        switch (restriction.getRestrictionType()) {
            case COUNT:
                builder.count(restriction.getCount());
                break;
            case END_DATE:
                builder.endDateOfEvents(restriction.getEndDate());
                break;
        }
    }

    @Override
    @Transactional
    public SubjectWithSubSubjectsAndSchedules deleteSubjectScheduleById(Long subjectScheduleId) throws IOException {
        SubjectSchedule subjectSchedule = subjectScheduleRepository.findById(subjectScheduleId)
                .orElseThrow(ResourceNotFoundException::new);
        subjectSchedule.setActive(false);
        eventService.cancelEvent(subjectSchedule.getGoogleEvent().getEventId());
        return fromSubjectSchedule(subjectScheduleRepository.saveAndFlush(subjectSchedule));
    }
}
