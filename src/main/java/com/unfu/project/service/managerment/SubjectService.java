package com.unfu.project.service.managerment;

import com.unfu.project.service.managerment.payload.request.CreateSubjectRequest;
import com.unfu.project.service.managerment.payload.response.SubjectDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.annotation.Nullable;
import java.util.List;

public interface SubjectService {

    List<SubjectDTO> getStudentSubjectsByStudentId(@Nullable Long studentId);

    Page<SubjectDTO> findAll(Pageable pageable);

    List<SubjectDTO> findAll();

    SubjectDTO save(CreateSubjectRequest request);

    SubjectDTO findById(Long id);
}
