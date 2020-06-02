package com.unfu.project.service.users;

import com.unfu.project.service.users.payload.request.EditProfileRequest;
import com.unfu.project.service.users.payload.response.PublicUserResponse;
import com.unfu.project.service.users.payload.response.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    UserResponse findCurrentUserResponse();

    UserResponse editCurrentUser(EditProfileRequest request);

    List<PublicUserResponse> findAllActive();

    List<PublicUserResponse> findTeachersUsers();

    List<PublicUserResponse> findStudentsUser();
}
