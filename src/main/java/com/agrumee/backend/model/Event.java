package com.agrumee.backend.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Event {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private LocalDateTime date;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String imageUrl;

    private int maxParticipants;

    private int minParticipants;

    private BigDecimal priceMin;

    private BigDecimal priceMax;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

    @ManyToOne
    @JoinColumn(name = "organized_by")
    private User organizedBy;

    @OneToOne
    @JoinColumn(name = "place_id")
    private Place place;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<Participation> participations = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "event_community_tags",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "community_tag_id"))
    private Set<CommunityTag> communityTags = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "event_accessibility_tags",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "accessibility_tag_id"))
    private Set<AccessibilityTag> accessibilityTags = new HashSet<>();

    @PrePersist
    public void onCreate() {
        createdAt = LocalDateTime.now();
        modifiedAt = createdAt;
    }

    @PreUpdate
    public void onUpdate() {
        modifiedAt = LocalDateTime.now();
    }
}

