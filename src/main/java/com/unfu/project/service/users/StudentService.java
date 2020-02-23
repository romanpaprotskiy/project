package com.unfu.project.service.users;

import antlr.collections.List;
import com.unfu.project.service.users.payload.response.StudentResponse;
import com.unfu.project.service.users.payload.response.StudentUserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StudentService {

    StudentResponse findResponseByUserId(Long userId);

    Page<StudentUserResponse> findAll(Pageable pageable);
}
