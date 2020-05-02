package com.unfu.project.domain.events;

import com.unfu.project.domain.management.Group;
import com.unfu.project.domain.management.SubGroup;
import com.unfu.project.domain.management.Subject;
import com.unfu.project.domain.users.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "event")
@Data
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "google_event")
    private GoogleEvent googleEvent;

    @Column(name = "target_type")
    @Enumerated(EnumType.STRING)
    private TargetType targetType;

    @ManyToMany
    @JoinTable(name = "event_user", joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    @EqualsAndHashCode.Exclude
    private Set<User> users = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    @ManyToOne
    @JoinColumn(name = "sub_group_id")
    private SubGroup subGroup;
}
