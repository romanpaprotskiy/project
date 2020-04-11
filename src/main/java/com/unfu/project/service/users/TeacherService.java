package com.unfu.project.service.users;

import com.unfu.project.service.users.payload.response.PublicTeacherResponse;
import com.unfu.project.service.users.payload.response.TeacherDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TeacherService {

    List<PublicTeacherResponse> findAll();

    Page<TeacherDTO> findAll(Pageable pageable);

    TeacherDTO findById(Long id);
}
