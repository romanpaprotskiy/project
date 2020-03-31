package com.unfu.project.service.managerment;

import com.unfu.project.service.managerment.payload.response.SubjectResponse;
import com.unfu.project.service.managerment.payload.response.SubjectWithParticipants;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.util.List;

public interface SubjectService {

    List<SubjectResponse> getStudentSubjectsByStudentId(Long studentId);

    Page<SubjectResponse> findAll(Pageable pageable);

    SubjectWithParticipants getBySubjectId(Long subjectId) throws IOException;
}
