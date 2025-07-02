package com.agrumee.backend.dto;

import java.time.LocalDateTime;
import java.util.Set;

public class EventSummaryDTO {
    private Long id;
    private String name;
    private String description;
    private String imageUrl;
    private int currentParticipants;
    private int maxParticipants;
    private LocalDateTime date;
    private String city;
    private Set<String> communityTags;
    private String eventType;


    public EventSummaryDTO() {
    }

    public EventSummaryDTO(Long id, String name, String description, String imageUrl,
                           int currentParticipants, int maxParticipants,
                           LocalDateTime date, String city, Set<String> communityTags, String eventType) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.currentParticipants = currentParticipants;
        this.maxParticipants = maxParticipants;
        this.date = date;
        this.city = city;
        this.communityTags = communityTags;
        this.eventType = eventType;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getImageUrl() { return imageUrl; }
    public int getCurrentParticipants() { return currentParticipants; }
    public int getMaxParticipants() { return maxParticipants; }
    public LocalDateTime getDate() { return date; }
    public String getCity() { return city; }
    public Set<String> getCommunityTags() { return communityTags; }
    public String getEventType() {
        return eventType;
    }
}
