package com.unfu.project.service.users;

import com.unfu.project.service.users.payload.request.CreateStudentRequest;
import com.unfu.project.service.users.payload.response.StudentResponse;
import com.unfu.project.service.users.payload.response.StudentUserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface StudentService {

    StudentResponse findResponseByUserId(Long userId);

    Page<StudentUserResponse> findAll(Pageable pageable);

    /**
     * @return map where key is group id and value is count of students in group
     */
    Map<Long, Long> countOfStudentsByGroupIds();

    StudentResponse createStudent(CreateStudentRequest request);
}
