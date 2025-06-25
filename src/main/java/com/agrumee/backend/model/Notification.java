package com.agrumee.backend.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Notification {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String message;

    private LocalDateTime createdAt;

    @ManyToOne
    private User user;

    @ManyToOne
    private Event event;

    @PrePersist
    public void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
