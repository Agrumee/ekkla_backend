package com.agrumee.backend.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String address;       // 12 Rue Exemple
    private String city;          // Paris
    private String postalCode;    // 75001
    private String country;       // France
    private boolean isPrivate;    // Si ce lieu est réservé ou non

    private LocalDateTime createdAt;

    private Double latitude;      // Coordonnées GPS depuis Google
    private Double longitude;

    private String googlePlaceId; // Optionnel : utile si tu veux faire une sync Google

    @OneToOne(mappedBy = "place")
    private Event event;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    // Getters, setters, etc.
}
