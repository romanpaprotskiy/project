package com.unfu.project.service.schedule;

import java.util.List;

public interface ScheduleDetailsService {

    List<String> getStudentsEmailByGroup(Long groupId);

    String getTeacherEmail(Long teacherId);

    String getSubjectName(Long subjectId);
}
