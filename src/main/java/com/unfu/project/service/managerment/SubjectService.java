package com.unfu.project.service.managerment;

import com.unfu.project.service.managerment.payload.request.CreateSubjectRequest;
import com.unfu.project.service.managerment.payload.response.SubjectResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.annotation.Nullable;
import java.util.List;

public interface SubjectService {

    List<SubjectResponse> getStudentSubjectsByStudentId(@Nullable Long studentId);

    Page<SubjectResponse> findAll(Pageable pageable);

    SubjectResponse save(CreateSubjectRequest request);
}
