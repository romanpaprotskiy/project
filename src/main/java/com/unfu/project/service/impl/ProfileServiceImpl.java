package com.unfu.project.service.impl;

import com.unfu.project.entity.User;
import com.unfu.project.payload.response.ProfileResponse;
import com.unfu.project.payload.response.UserResponse;
import com.unfu.project.repository.UserRepository;
import com.unfu.project.service.ProfileService;
import com.unfu.project.service.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class ProfileServiceImpl implements ProfileService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public ProfileServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public ProfileResponse getCurrentUserProfile() {
        User current = userRepository.findCurrent();
        UserResponse response = userMapper.map(current);
        return ProfileResponse.builder()
                .user(response)
                .build();
    }
}
