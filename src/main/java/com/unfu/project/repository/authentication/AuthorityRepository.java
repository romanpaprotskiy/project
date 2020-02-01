package com.unfu.project.repository.authentication;

import com.unfu.project.domain.authentication.Authority;
import com.unfu.project.domain.authentication.enumeration.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {

    boolean existsByAuthority(Role role);

    Optional<Authority> findByAuthority(Role role);
}
