package com.unfu.project.domain.management;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "sub_subject")
@Data
public class SubSubject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;
}
