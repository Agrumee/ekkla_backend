package com.agrumee.backend.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String pseudo;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private String profilePicture;
    private LocalDate birthDate;
    private String bio;
    private String job;
    private String pronouns;

    @OneToMany(mappedBy = "createdBy", cascade = CascadeType.ALL)
    private List<Participation> participations;

    @OneToMany(mappedBy = "organizedBy")
    private List<Event> events;

    @OneToMany(mappedBy = "user")
    private List<Notification> notifications;

    // Getters & setters
}
