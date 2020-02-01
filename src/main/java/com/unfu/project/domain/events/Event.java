package com.unfu.project.domain.events;

import com.unfu.project.domain.management.Group;
import com.unfu.project.domain.management.Subject;
import com.unfu.project.domain.users.Student;
import com.unfu.project.domain.users.Teacher;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "event")
@Data
public class Event implements Serializable {

    private static final long serialVersionUID = -2020155727722390748L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "google_event")
    private GoogleEvent googleEvent;

    @ManyToMany
    @JoinTable(name = "event_subject", joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id"))
    @EqualsAndHashCode.Exclude
    private Set<Subject> subjects = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "event_student", joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    @EqualsAndHashCode.Exclude
    private Set<Student> students = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "event_teacher", joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "teacher_id"))
    @EqualsAndHashCode.Exclude
    private Set<Teacher> teachers = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "event_group", joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id"))
    @EqualsAndHashCode.Exclude
    private Set<Group> groups = new HashSet<>();
}
