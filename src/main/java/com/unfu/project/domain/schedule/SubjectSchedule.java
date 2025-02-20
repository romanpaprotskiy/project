package com.unfu.project.domain.schedule;

import com.unfu.project.domain.management.Group;
import com.unfu.project.domain.management.Subject;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@ToString(callSuper = true)
@DiscriminatorValue("SUBJECT")
public class SubjectSchedule extends Schedule {

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;
}
