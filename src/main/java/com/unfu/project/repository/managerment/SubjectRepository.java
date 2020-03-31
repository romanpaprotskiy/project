package com.unfu.project.repository.managerment;

import com.unfu.project.domain.management.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {

    @Query(value = "select distinct s from Subject s " +
            "join s.subjectSchedules ss " +
            "join ss.group g " +
            "join g.students st where st.id = ?1 order by s.name")
    List<Subject> findAllByStudentId(Long studentId);

    @Query("select s from Subject s left join fetch s.subjectSchedules ss " +
            "left join fetch ss.group g " +
            "left join fetch g.students gs " +
            "left join fetch g.subGroups gss where s.id = ?1")
    Optional<Subject> findByIdAndFetch(Long id);
}
