package com.unfu.project.service.users;

import com.unfu.project.service.users.payload.request.CreateStudentRequest;
import com.unfu.project.service.users.payload.response.StudentDTO;
import com.unfu.project.service.users.payload.response.StudentUserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

public interface StudentService {

    Optional<StudentDTO> findResponseByUserId(Long userId);

    Page<StudentUserResponse> findAll(Pageable pageable);

    /**
     * @return map where key is group id and value is count of students in group
     */
    Map<Long, Long> countOfStudentsByGroupIds();

    StudentDTO createStudent(CreateStudentRequest request);

    Set<StudentDTO> getByGroupId(Long groupId);
}
