package com.unfu.project.repository.users;

import com.unfu.project.domain.users.Student;
import com.unfu.project.service.managerment.payload.GroupStudentsCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByUserId(Long userId);

    @Query("select new com.unfu.project.service.managerment.payload.GroupStudentsCount(g.id, count(s.id)) " +
            "from Student s join s.group g group by g.id")
    Collection<GroupStudentsCount> countStudentsByGroupIdIn();
}
