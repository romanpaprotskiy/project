package com.unfu.project.repository.users;

import com.unfu.project.domain.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    @Query(value = "select u from User u where u.email = ?#{principal?.username}")
    User findCurrent();

    Collection<User> findAllByActive(boolean active);
}
