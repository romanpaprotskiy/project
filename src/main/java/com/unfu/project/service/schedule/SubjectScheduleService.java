package com.unfu.project.service.schedule;

import com.unfu.project.service.schedule.payload.response.SubjectWithSubSubjectsAndSchedules;

import java.util.List;

public interface SubjectScheduleService {

    List<SubjectWithSubSubjectsAndSchedules> getWithSubSubjectsBySubjectId(Long subjectId);
}
