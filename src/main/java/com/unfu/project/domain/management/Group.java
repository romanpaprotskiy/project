package com.unfu.project.domain.management;

import com.unfu.project.domain.events.Event;
import com.unfu.project.domain.users.Student;
import com.unfu.project.domain.users.Teacher;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "students_group")
@Data
@NoArgsConstructor
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "group")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<SubGroup> subGroups = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "guide_id")
    private Teacher guide;

    @OneToMany(mappedBy = "group")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Student> students = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "event_group", joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id"))
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Event> events = new HashSet<>();

    private Group(Long id) {
        this.id = id;
    }

    public static Group fromId(Long id) {
        return new Group(id);
    }
}
