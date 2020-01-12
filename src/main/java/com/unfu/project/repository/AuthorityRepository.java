package com.unfu.project.repository;

import com.unfu.project.entity.Authority;
import com.unfu.project.entity.constants.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {

    boolean existsByAuthority(Role role);

    Optional<Authority> findByAuthority(Role role);
}
