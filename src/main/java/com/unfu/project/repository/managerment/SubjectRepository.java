package com.unfu.project.repository.managerment;

import com.unfu.project.domain.management.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {

    @Query(value = "select distinct s from Subject s " +
            "join s.subjectSchedules ss " +
            "join ss.group g " +
            "join g.students st where st.id = ?1 order by s.name")
    List<Subject> findAllByStudentId(Long studentId);

}
