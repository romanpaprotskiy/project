package com.unfu.project.service.schedule;

import com.unfu.project.service.schedule.payload.request.ScheduleCreateRequest;
import com.unfu.project.service.schedule.payload.request.ScheduleUpdateRequest;
import com.unfu.project.service.schedule.payload.response.SubjectWithSubSubjectsAndSchedules;

import java.io.IOException;
import java.util.List;

public interface SubjectScheduleService {

    List<SubjectWithSubSubjectsAndSchedules> getWithSubSubjectsBySubjectId(Long subjectId);

    SubjectWithSubSubjectsAndSchedules create(ScheduleCreateRequest request) throws IOException;

    SubjectWithSubSubjectsAndSchedules update(ScheduleUpdateRequest request) throws IOException;

    SubjectWithSubSubjectsAndSchedules deleteSubjectScheduleById(Long subjectScheduleId) throws IOException;
}
