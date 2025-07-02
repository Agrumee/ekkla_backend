package com.agrumee.backend.service;

import com.agrumee.backend.dto.EventSummaryDTO;
import com.agrumee.backend.model.Event;
import com.agrumee.backend.model.Tag;
import com.agrumee.backend.repository.EventRepository;
import com.agrumee.backend.repository.ParticipationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final ParticipationRepository participationRepository;

    public EventService(EventRepository eventRepository, ParticipationRepository participationRepository) {
        this.eventRepository = eventRepository;
        this.participationRepository = participationRepository;
    }

    public List<EventSummaryDTO> getUpcomingEventSummaries() {
        LocalDateTime now = LocalDateTime.now();
        List<Event> events = eventRepository.findByDateAfterOrderByDateAsc(now);

        return events.stream()
                .filter(event -> event.getEventType() != null &&
                        !"PRIVATE".equalsIgnoreCase(event.getEventType().getLabel()))
                .map(event -> {
                    int currentParticipants = participationRepository.countByEventIdAndIsConfirmedTrue(event.getId());

                    return new EventSummaryDTO(
                            event.getId(),
                            event.getName(),
                            event.getDescription(),
                            event.getImageUrl(),
                            currentParticipants,
                            event.getMaxParticipants(),
                            event.getDate(),
                            event.getPlace() != null ? event.getPlace().getCity() : null,
                            event.getTags().stream()
                                    .filter(tag -> tag.getType() != null &&
                                            "COMMUNITY".equalsIgnoreCase(tag.getType().getLabel()))
                                    .map(Tag::getLabel)
                                    .collect(Collectors.toSet()),
                            event.getEventType().getLabel()
                    );
                })
                .collect(Collectors.toList());
    }
}
