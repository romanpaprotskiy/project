package com.unfu.project.repository.schedule;

import com.unfu.project.domain.schedule.SubjectSchedule;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Repository
public interface SubjectScheduleRepository extends ScheduleRepository<SubjectSchedule> {

    Collection<SubjectSchedule> findAllBySubjectIdAndActive(Long subjectId, Boolean active);

    @Query("select ss from SubjectSchedule ss join ss.googleEvent ge where ge.eventId in ?1")
    List<SubjectSchedule> findAllByGoogleEventEventId(Collection<String> eventIds);
}
