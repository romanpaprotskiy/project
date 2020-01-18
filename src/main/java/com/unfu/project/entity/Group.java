package com.unfu.project.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "students_group")
@Data
public class Group implements Serializable {

    private static final long serialVersionUID = 1317900474484227262L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Group parent;

    @ManyToOne
    @JoinColumn(name = "guide_id")
    private Teacher guide;

    @OneToMany(mappedBy = "group")
    private Set<Student> students = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "event_group", joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id"))
    @EqualsAndHashCode.Exclude
    private Set<Group> groups = new HashSet<>();
}
