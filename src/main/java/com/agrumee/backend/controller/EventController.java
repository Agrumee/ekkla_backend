package com.agrumee.backend.controller;

import com.agrumee.backend.dto.EventSummaryDTO;
import com.agrumee.backend.model.Event;
import com.agrumee.backend.service.EventService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class    EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public List<EventSummaryDTO> getUpcomingEvents() {
        return eventService.getUpcomingEventSummaries();
    }
}
