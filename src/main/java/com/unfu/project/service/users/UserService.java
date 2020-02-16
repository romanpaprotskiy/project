package com.unfu.project.service.users;

import com.unfu.project.service.users.payload.request.EditProfileRequest;
import com.unfu.project.service.users.payload.response.UserResponse;

public interface UserService {

    UserResponse findCurrentUserResponse();

    UserResponse editCurrentUser(EditProfileRequest request);
}
