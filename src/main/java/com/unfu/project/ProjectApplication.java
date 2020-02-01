package com.unfu.project;

import com.unfu.project.domain.authentication.Authority;
import com.unfu.project.domain.authentication.enumeration.Role;
import com.unfu.project.repository.authentication.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.List;

@SpringBootApplication
public class ProjectApplication {

    @Autowired
    private AuthorityRepository authorityRepository;

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

    public static void main(String[] args) {
        SpringApplication.run(ProjectApplication.class, args);
    }

}
