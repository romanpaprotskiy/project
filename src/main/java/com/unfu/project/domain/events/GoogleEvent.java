package com.unfu.project.domain.events;

import com.unfu.project.domain.users.User;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(name = "google_event")
@Data
public class GoogleEvent implements Serializable {

    private static final long serialVersionUID = 7466651570144606590L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private User creator;

    @Column(name = "created_at")
    private Instant createdAt = Instant.now();

    @Column(name = "event_id", unique = true)
    private String eventId;

    @Column(name = "recurrent")
    private Boolean recurrent;
}
