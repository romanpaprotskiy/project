package com.unfu.project.service.mapper;

import com.unfu.project.entity.User;
import com.unfu.project.payload.response.UserResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {AuthorityMapper.class})
public interface UserMapper {

    UserResponse map(User user);
}
