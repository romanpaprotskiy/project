package com.unfu.project.service.schedule.payload.response;

import com.unfu.project.service.events.payload.response.GoogleRecurrentEventResponse;
import com.unfu.project.service.managerment.payload.response.GroupWithStudents;
import com.unfu.project.service.users.payload.response.TeacherDTO;
import lombok.Builder;
import lombok.Data;

import java.util.Comparator;

@Data
@Builder
public final class SubjectWithSubSubjectsAndSchedules
        implements Comparable<SubjectWithSubSubjectsAndSchedules> {

    private final Long subjectId;

    private final Long scheduleId;

    private final GroupWithStudents group;

    private final TeacherDTO teacher;

    private final String eventId;

    private final GoogleRecurrentEventResponse schedule;

    @Override
    public int compareTo(SubjectWithSubSubjectsAndSchedules o) {
        return Comparator.comparing(SubjectWithSubSubjectsAndSchedules::getSchedule,
                Comparator.nullsLast(Comparator.naturalOrder()))
                .compare(this, o);
    }
}
