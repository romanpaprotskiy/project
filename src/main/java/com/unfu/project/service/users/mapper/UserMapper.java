package com.unfu.project.service.users.mapper;

import com.unfu.project.domain.users.User;
import com.unfu.project.service.authentication.mapper.AuthorityMapper;
import com.unfu.project.service.users.payload.request.EditProfileRequest;
import com.unfu.project.service.users.payload.response.PublicUserResponse;
import com.unfu.project.service.users.payload.response.UserResponse;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {AuthorityMapper.class})
public interface UserMapper {

    UserResponse map(User user);

    PublicUserResponse mapToPublic(User user);

    default User edit(User user, EditProfileRequest request) {
        User newUser = new User(user);
        newUser.setBirthDate(request.getBirthDate());
        newUser.setPhone(request.getPhone());
        newUser.setSkypeId(request.getSkypeId());
        return newUser;
    }

    default User fromId(Long id) {
        if (id == null) return null;
        return User.builder().id(id).build();
    }
}
