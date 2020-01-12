package com.unfu.project;

import com.unfu.project.entity.Authority;
import com.unfu.project.entity.constants.Role;
import com.unfu.project.repository.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class ProjectApplication {

    @Autowired
    private AuthorityRepository authorityRepository;

    @PostConstruct
    public void initAuthority() {
        Authority guest = Authority.fromRole(Role.GUEST);
        Authority student = Authority.fromRole(Role.STUDENT);
        Authority teacher = Authority.fromRole(Role.TEACHER);
        Authority admin = Authority.fromRole(Role.ADMIN);
        List<Authority> authorities = Arrays.asList(guest, student, teacher, admin);
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
