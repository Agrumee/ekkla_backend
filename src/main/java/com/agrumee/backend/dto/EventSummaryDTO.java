package com.agrumee.backend.dto;

import java.time.LocalDateTime;
import java.util.Set;

public record EventSummaryDTO(
        Long id,
        String name,
        String description,
        String imageUrl,
        int currentParticipants,
        int maxParticipants,
        LocalDateTime date,
        String city,
        Set<String> communityTags,
        String eventType
) {}
