package com.unfu.project.domain.users;

import com.unfu.project.domain.users.Teacher;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "science_title")
@Data
public class ScienceTitle implements Serializable {

    private static final long serialVersionUID = -676092950973840837L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "sequence")
    private Integer sequence;

    @OneToMany(mappedBy = "scienceTitle")
    @EqualsAndHashCode.Exclude
    private Set<Teacher> teachers = new HashSet<>();
}
