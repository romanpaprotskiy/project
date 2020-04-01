package com.unfu.project.config;

import com.unfu.project.domain.authentication.Authority;
import com.unfu.project.domain.authentication.enumeration.Role;
import com.unfu.project.repository.authentication.AuthorityRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.List;

@Configuration
@AllArgsConstructor
public class AuthorityInitializationConfig {

    private final AuthorityRepository authorityRepository;

    @PostConstruct
    public void initAuthority() {
        var guest = Authority.fromRole(Role.GUEST);
        var student = Authority.fromRole(Role.STUDENT);
        var teacher = Authority.fromRole(Role.TEACHER);
        var admin = Authority.fromRole(Role.ADMIN);
        var authorities = List.of(guest, student, teacher, admin);
        for (Authority authority : authorities) {
            if (!authorityRepository.existsByAuthority(Role.fromString(authority.getAuthority()))) {
                authorityRepository.save(authority);
            }
        }
    }
}
