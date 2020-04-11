package com.unfu.project.service.schedule.impl;

import com.unfu.project.service.managerment.SubjectService;
import com.unfu.project.service.schedule.ScheduleDetailsService;
import com.unfu.project.service.users.StudentService;
import com.unfu.project.service.users.TeacherService;
import com.unfu.project.service.users.payload.response.StudentDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ScheduleDetailsServiceImpl implements ScheduleDetailsService {

    private final StudentService studentService;

    private final SubjectService subjectService;

    private final TeacherService teacherService;

    @Override
    public List<String> getStudentsEmailByGroup(Long groupId) {
        return studentService.getByGroupId(groupId).stream()
                .map(StudentDTO::getUserEmail)
                .collect(Collectors.toList());
    }

    @Override
    public String getTeacherEmail(Long teacherId) {
        return teacherService.findById(teacherId).getEmail();
    }

    @Override
    public String getSubjectName(Long subjectId) {
        return subjectService.findById(subjectId).getName();
    }
}
