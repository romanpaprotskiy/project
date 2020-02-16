package com.unfu.project.service.users.impl;

import com.unfu.project.domain.users.User;
import com.unfu.project.exception.users.UserNotFoundException;
import com.unfu.project.repository.users.UserRepository;
import com.unfu.project.service.authentication.util.SecurityUtils;
import com.unfu.project.service.users.UserService;
import com.unfu.project.service.users.mapper.UserMapper;
import com.unfu.project.service.users.payload.request.EditProfileRequest;
import com.unfu.project.service.users.payload.response.UserResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Override
    public UserResponse findCurrentUserResponse() {
        User current = userRepository.findCurrent();
        return userMapper.map(current);
    }

    @Override
    public UserResponse editCurrentUser(EditProfileRequest request) {
        Long currentUserId = SecurityUtils.getCurrentUserId();
        User user = userRepository.findById(currentUserId).orElseThrow(UserNotFoundException::new);
        User edit = userMapper.edit(user, request);
        return userMapper.map(userRepository.save(edit));
    }
}
