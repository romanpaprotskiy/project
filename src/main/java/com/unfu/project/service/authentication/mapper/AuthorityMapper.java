package com.unfu.project.service.authentication.mapper;

import com.unfu.project.domain.authentication.Authority;
import com.unfu.project.service.authentication.payload.response.AuthorityResponse;
import org.mapstruct.Mapper;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface AuthorityMapper {

    AuthorityResponse map(Authority authority);

    Set<AuthorityResponse> map(Set<Authority> authorities);

    Set<AuthorityResponse> map(Collection<? extends GrantedAuthority> authorities);
}
