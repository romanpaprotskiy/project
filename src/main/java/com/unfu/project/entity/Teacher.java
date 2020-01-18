package com.unfu.project.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "teacher")
@Data
public class Teacher implements Serializable {

    private static final long serialVersionUID = 8030406376868185637L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "science_title_id")
    private ScienceTitle scienceTitle;

    @Column(name = "employed_from")
    private LocalDate employedFrom;

    @ManyToMany
    @JoinTable(name = "event_teacher", joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id"))
    @EqualsAndHashCode.Exclude
    private Set<Event> events = new HashSet<>();
}
