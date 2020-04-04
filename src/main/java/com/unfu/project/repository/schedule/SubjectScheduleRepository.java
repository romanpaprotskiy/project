package com.unfu.project.repository.schedule;

import com.unfu.project.domain.schedule.SubjectSchedule;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface SubjectScheduleRepository extends ScheduleRepository<SubjectSchedule> {

    Collection<SubjectSchedule> findAllBySubjectId(Long subjectId);
}
