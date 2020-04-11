package com.unfu.project.service.schedule;

import com.unfu.project.service.schedule.payload.request.CreateScheduleRequest;
import com.unfu.project.service.schedule.payload.response.SubjectWithSubSubjectsAndSchedules;

import java.io.IOException;
import java.util.List;

public interface SubjectScheduleService {

    List<SubjectWithSubSubjectsAndSchedules> getWithSubSubjectsBySubjectId(Long subjectId);

    SubjectWithSubSubjectsAndSchedules create(CreateScheduleRequest request) throws IOException;
}
