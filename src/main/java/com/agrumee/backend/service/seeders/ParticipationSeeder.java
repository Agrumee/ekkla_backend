package com.agrumee.backend.service.seeders;

import com.agrumee.backend.model.Event;
import com.agrumee.backend.model.Participation;
import com.agrumee.backend.model.User;
import com.agrumee.backend.repository.EventRepository;
import com.agrumee.backend.repository.ParticipationRepository;
import com.agrumee.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import net.datafaker.Faker;


@Service
public class ParticipationSeeder {

    private final Faker faker = new Faker();
    private final ParticipationRepository participationRepository;
    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final Random random = new Random();

    public ParticipationSeeder(ParticipationRepository participationRepository, EventRepository eventRepository, UserRepository userRepository) {
        this.participationRepository = participationRepository;
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    public void seed(int count) {
        List<User> users = userRepository.findAll();
        List<Event> events = eventRepository.findAll();

        if (users.isEmpty() || events.isEmpty()) return;

        IntStream.range(0, count).forEach(i -> {
            Participation participation = new Participation();
            participation.setMessage(faker.lorem().sentence());
            participation.setCreatedBy(users.get(random.nextInt(users.size())));
            participation.setEvent(events.get(random.nextInt(events.size())));
            participation.setIsConfirmed(random.nextDouble() < 0.9);
            participationRepository.save(participation);
        });
    }
}
