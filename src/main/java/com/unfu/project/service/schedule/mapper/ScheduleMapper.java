package com.unfu.project.service.schedule.mapper;

import com.google.api.services.calendar.model.Event;
import com.unfu.project.domain.events.GoogleEvent;
import com.unfu.project.domain.schedule.SubjectSchedule;
import com.unfu.project.domain.users.User;
import com.unfu.project.service.authentication.util.SecurityUtils;
import com.unfu.project.service.managerment.mapper.GroupMapper;
import com.unfu.project.service.managerment.mapper.SubjectMapper;
import com.unfu.project.service.schedule.payload.request.ScheduleCreateRequest;
import com.unfu.project.service.schedule.payload.request.ScheduleUpdateRequest;
import com.unfu.project.service.users.mapper.TeacherMapper;
import org.mapstruct.*;

import java.time.Instant;

@Mapper(componentModel = "spring", uses = {TeacherMapper.class, GroupMapper.class,
        SubjectMapper.class})
public interface ScheduleMapper {

    @Mapping(source = "groupId", target = "group")
    @Mapping(source = "teacherId", target = "teacher")
    @Mapping(source = "subjectId", target = "subject")
    SubjectSchedule mapFromRequest(ScheduleCreateRequest request, @Context Event googleEvent);

    @Mapping(source = "groupId", target = "group")
    @Mapping(source = "teacherId", target = "teacher")
    @Mapping(source = "subjectId", target = "subject")
    SubjectSchedule mapFromRequest(ScheduleUpdateRequest request, @Context Event googleEvent,
                                   @Context Long googleEventId);

    @AfterMapping
    default void createGoogleEvent(@MappingTarget SubjectSchedule subjectSchedule,
                                          @Context Event googleEvent) {
        GoogleEvent event = new GoogleEvent();
        event.setRecurrent(googleEvent.getRecurrence() != null);
        event.setEventId(googleEvent.getId());
        event.setCreatedAt(Instant.now());
        event.setCreator(User.fromId(SecurityUtils.getCurrentUserId()));
        subjectSchedule.setGoogleEvent(event);
    }

    @AfterMapping
    default void createGoogleEvent(@MappingTarget SubjectSchedule subjectSchedule,
                                   @Context Event googleEvent, @Context Long googleEventId) {
        GoogleEvent event = new GoogleEvent();
        event.setId(googleEventId);
        event.setRecurrent(googleEvent.getRecurrence() != null);
        event.setEventId(googleEvent.getId());
        event.setCreatedAt(Instant.now());
        event.setCreator(User.fromId(SecurityUtils.getCurrentUserId()));
        subjectSchedule.setGoogleEvent(event);
    }

}
