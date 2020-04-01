package com.unfu.project.repository.managerment;

import com.unfu.project.domain.management.Group;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

    @Query("select g from Group g")
    @EntityGraph(attributePaths = {"subGroups", "students", "subGroups.students"})
    Page<Group> findAllAndFetch(Pageable pageable);
}
