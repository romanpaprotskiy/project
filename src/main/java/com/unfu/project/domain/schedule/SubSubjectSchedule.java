package com.unfu.project.domain.schedule;

import com.unfu.project.domain.management.SubGroup;
import com.unfu.project.domain.management.SubSubject;
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
@DiscriminatorValue("SUB_SUBJECT")
public class SubSubjectSchedule extends Schedule {

    @ManyToOne
    @JoinColumn(name = "sub_group_id")
    private SubGroup subGroup;

    @ManyToOne
    @JoinColumn(name = "sub_subject_id")
    private SubSubject subSubject;
}
