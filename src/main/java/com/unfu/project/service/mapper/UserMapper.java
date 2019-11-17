package com.unfu.project.service.mapper;

import com.unfu.project.entity.User;
import com.unfu.project.payload.response.UserResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {AuthorityMapper.class})
public abstract class UserMapper {

    public abstract UserResponse map(User user);
}
