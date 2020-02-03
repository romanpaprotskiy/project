package com.unfu.project.service.users;

import com.unfu.project.service.users.payload.response.StudentResponse;

public interface StudentService {

    StudentResponse findResponseByUserId(Long userId);
}
