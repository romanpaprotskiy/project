package com.unfu.project.service.mapper;

import com.unfu.project.entity.Authority;
import com.unfu.project.payload.response.AuthorityResponse;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public abstract class AuthorityMapper {

    public abstract AuthorityResponse map(Authority authority);

    public abstract Set<AuthorityResponse> map(Set<Authority> authorities);
}
