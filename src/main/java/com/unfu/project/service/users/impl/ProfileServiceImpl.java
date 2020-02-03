package com.unfu.project.service.users.impl;

import com.unfu.project.service.users.ProfileService;
import com.unfu.project.service.users.StudentService;
import com.unfu.project.service.users.UserService;
import com.unfu.project.service.users.payload.response.ProfileResponse;
import com.unfu.project.service.users.payload.response.StudentResponse;
import com.unfu.project.service.users.payload.response.UserResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final UserService userService;

    private final StudentService studentService;

    @Override
    public ProfileResponse getCurrentUserProfile() {
        UserResponse userResponse = userService.findCurrentUserResponse();
        StudentResponse studentResponse = studentService.findResponseByUserId(userResponse.getId());
        return ProfileResponse.builder()
                .user(userResponse)
                .student(studentResponse)
                .build();
    }
}
