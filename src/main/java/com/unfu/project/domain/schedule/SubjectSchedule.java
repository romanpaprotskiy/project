package com.unfu.project.domain.schedule;

import com.unfu.project.domain.events.GoogleEvent;
import com.unfu.project.domain.management.Group;
import com.unfu.project.domain.management.Subject;
import com.unfu.project.domain.users.Teacher;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "subject_schedule")
@Data
public class SubjectSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "google_event_id")
    private GoogleEvent googleEvent;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @Column(name = "active")
    private Boolean active;
}
