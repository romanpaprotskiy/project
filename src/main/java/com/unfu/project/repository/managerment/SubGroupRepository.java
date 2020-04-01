package com.unfu.project.repository.managerment;

import com.unfu.project.domain.management.SubGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface SubGroupRepository extends JpaRepository<SubGroup, Long> {

    Collection<SubGroup> findAllByGroupId(Long id);
}
