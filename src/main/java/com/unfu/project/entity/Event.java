package com.unfu.project.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
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

    @Column(name = "event_id", unique = true)
    private String eventId;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private User creator;

    @Column(name = "created_at")
    private Instant createdAt;

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
