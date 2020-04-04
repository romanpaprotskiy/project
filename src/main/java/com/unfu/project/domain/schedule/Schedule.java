package com.unfu.project.domain.schedule;

import com.unfu.project.domain.events.GoogleEvent;
import com.unfu.project.domain.users.Teacher;
import lombok.Data;
import org.hibernate.annotations.DiscriminatorOptions;

import javax.persistence.*;

@Table(name = "schedule")
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorOptions(force = true)
@Data
abstract class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @OneToOne
    @JoinColumn(name = "google_event_id")
    protected GoogleEvent googleEvent;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    protected Teacher teacher;

    @Column(name = "active")
    protected Boolean active;
}
