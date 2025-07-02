package com.agrumee.backend.service.seeders;

import com.agrumee.backend.model.Event;
import com.agrumee.backend.model.EventType;
import com.agrumee.backend.model.Place;
import com.agrumee.backend.model.User;
import com.agrumee.backend.repository.EventRepository;
import com.agrumee.backend.repository.EventTypeRepository;
import com.agrumee.backend.repository.PlaceRepository;
import com.agrumee.backend.repository.UserRepository;
import net.datafaker.Faker;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.IntStream;

@Service
public class EventSeeder {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final PlaceRepository placeRepository;
    private final EventTypeRepository eventTypeRepository;
    private final Faker faker = new Faker();
    private final Random random = new Random();

    public EventSeeder(EventRepository eventRepository,
                       UserRepository userRepository,
                       PlaceRepository placeRepository,
                       EventTypeRepository eventTypeRepository) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
        this.placeRepository = placeRepository;
        this.eventTypeRepository = eventTypeRepository;
    }

    public void seed(int count) {
        List<User> users = userRepository.findAll();
        List<Place> places = placeRepository.findAll();
        List<EventType> eventTypes = eventTypeRepository.findAll();

        if (users.isEmpty() || places.isEmpty() || eventTypes.isEmpty()) {
            throw new IllegalStateException("Users, places, and event types must be seeded before events.");
        }

        IntStream.range(0, count).forEach(i -> {
            Event event = new Event();
            event.setName(faker.book().title());
            event.setDescription(faker.lorem().paragraph());
            event.setImageUrl(faker.internet().image());
            event.setDate(faker.date().future(30, java.util.concurrent.TimeUnit.DAYS)
                    .toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime());
            event.setCreatedAt(LocalDateTime.now(ZoneOffset.UTC));
            event.setModifiedAt(LocalDateTime.now(ZoneOffset.UTC));
            event.setPriceMin(BigDecimal.valueOf(random.nextInt(10)));
            event.setPriceMax(BigDecimal.valueOf(10 + random.nextInt(40)));
            event.setMaxParticipants(10 + random.nextInt(10));
            event.setOrganizedBy(users.get(random.nextInt(users.size())));
            event.setPlace(places.get(random.nextInt(places.size())));
            event.setEventType(eventTypes.get(random.nextInt(eventTypes.size())));

            eventRepository.save(event);
        });
    }
}
