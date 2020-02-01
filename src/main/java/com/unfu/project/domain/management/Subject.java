package com.unfu.project.domain.management;

import com.unfu.project.domain.schedule.SubjectSchedule;
import com.unfu.project.domain.events.Event;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "subject")
@Data
public class Subject implements Serializable {

    private static final long serialVersionUID = 3909860585371887222L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "subject")
    @EqualsAndHashCode.Exclude
    private Set<SubjectSchedule> subjectSchedules = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "event_subject", joinColumns = @JoinColumn(name = "subject_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id"))
    @EqualsAndHashCode.Exclude
    private Set<Event> events = new HashSet<>();
}
