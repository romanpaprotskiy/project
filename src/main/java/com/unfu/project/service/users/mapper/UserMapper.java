package com.unfu.project.service.users.mapper;

import com.unfu.project.domain.users.User;
import com.unfu.project.service.users.payload.response.UserResponse;
import com.unfu.project.service.authentication.mapper.AuthorityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {AuthorityMapper.class})
public interface UserMapper {

    UserResponse map(User user);
}
