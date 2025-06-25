package com.agrumee.backend.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Participation {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean isConfirmed;

    @Column(columnDefinition = "TEXT")
    private String message;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @PrePersist
    public void onCreate() {
        createdAt = LocalDateTime.now();
    }
}

