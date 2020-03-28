package com.unfu.project.repository.managerment;

import com.unfu.project.domain.management.Group;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

    @EntityGraph(attributePaths = {"subGroups"})
    List<Group> findAllByParentIsNull();
}
