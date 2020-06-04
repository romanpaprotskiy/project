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

    List<User> findAllByEmailIn(Collection<String> emails);

    @Query(value = "select u from User u where (u.id not in " +
            "(select s.user.id from Student s)) " +
            "and (u.id not in (select t.user.id from Teacher t)) " +
            "and u.active = ?1")
    Collection<User> findAllByActive(boolean active);

    @Query("select u from User u where u.firstName like concat(?1, '%') or u.lastName like concat(?1, '%')")
    List<User> findAll(String search);

    @Query("select t.user from Teacher t where t.user is not null")
    List<User> findAllIsTeacher();

    @Query("select s.user from Student s where s.user is not null")
    List<User> findAllIsStudent();
}
