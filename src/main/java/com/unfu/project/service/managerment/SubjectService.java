package com.unfu.project.service.managerment;

import com.unfu.project.service.managerment.payload.response.SubjectResponse;

import java.util.List;

public interface SubjectService {

    List<SubjectResponse> getStudentSubjectsByStudentId(Long studentId);
}
