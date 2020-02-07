package com.unfu.project.service.users.impl;

import com.unfu.project.service.managerment.SubjectService;
import com.unfu.project.service.managerment.payload.response.SubjectResponse;
import com.unfu.project.service.users.ProfileService;
import com.unfu.project.service.users.StudentService;
import com.unfu.project.service.users.UserService;
import com.unfu.project.service.users.payload.response.ProfileResponse;
import com.unfu.project.service.users.payload.response.StudentResponse;
import com.unfu.project.service.users.payload.response.UserResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final UserService userService;

    private final StudentService studentService;

    private final SubjectService subjectService;

    @Override
    public ProfileResponse getCurrentUserProfile() {
        UserResponse userResponse = userService.findCurrentUserResponse();
        StudentResponse studentResponse = studentService.findResponseByUserId(userResponse.getId());
        List<SubjectResponse> subjects = subjectService.getStudentSubjectsByStudentId(studentResponse.getId());
        return ProfileResponse.builder()
                .user(userResponse)
                .student(studentResponse)
                .subjects(subjects)
                .build();
    }
}
