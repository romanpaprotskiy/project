package com.unfu.project.repository.managerment;

import com.unfu.project.domain.management.Group;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

    @EntityGraph(attributePaths = {"subGroups"})
    List<Group> findAllByParentIsNull();

    @Query("select g from Group g left join fetch g.subGroups sg order by g.parent.id, g.id")
    Set<Group> findAllFetchSubGroups();
}
