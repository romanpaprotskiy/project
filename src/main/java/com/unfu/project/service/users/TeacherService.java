package com.unfu.project.service.users;

import com.unfu.project.service.users.payload.response.PublicTeacherResponse;

import java.util.List;

public interface TeacherService {

    List<PublicTeacherResponse> findAll();
}
