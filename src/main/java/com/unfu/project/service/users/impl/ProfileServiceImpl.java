package com.unfu.project.service.users.impl;

import com.unfu.project.domain.users.User;
import com.unfu.project.service.users.payload.response.ProfileResponse;
import com.unfu.project.service.users.payload.response.UserResponse;
import com.unfu.project.repository.users.UserRepository;
import com.unfu.project.service.users.ProfileService;
import com.unfu.project.service.users.mapper.UserMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Override
    public ProfileResponse getCurrentUserProfile() {
        User current = userRepository.findCurrent();
        UserResponse response = userMapper.map(current);
        return ProfileResponse.builder()
                .user(response)
                .build();
    }
}
