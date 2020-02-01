package com.unfu.project.domain.users;

import com.unfu.project.domain.events.Event;
import com.unfu.project.domain.management.Group;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "student")
@Data
public class Student implements Serializable {

    private static final long serialVersionUID = -6434021030150767018L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "date_of_enroll")
    private LocalDate dateOfEnroll;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    @ManyToMany
    @JoinTable(name = "event_student", joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id"))
    @EqualsAndHashCode.Exclude
    private Set<Event> events = new HashSet<>();
}
