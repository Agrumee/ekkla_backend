package com.agrumee.backend.service.seeders;

import com.agrumee.backend.model.Event;
import com.agrumee.backend.model.Notification;
import com.agrumee.backend.model.User;
import com.agrumee.backend.repository.EventRepository;
import com.agrumee.backend.repository.NotificationRepository;
import com.agrumee.backend.repository.UserRepository;
import net.datafaker.Faker;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

@Service
public class NotificationSeeder {

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    private final Faker faker = new Faker();
    private final Random random = new Random();

    public NotificationSeeder(NotificationRepository notificationRepository, UserRepository userRepository, EventRepository eventRepository) {
        this.notificationRepository = notificationRepository;
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
    }

    public void seed(int count) {
        List<User> users = userRepository.findAll();
        List<Event> events = eventRepository.findAll();

        if (users.isEmpty()) return;

        if (events.isEmpty()) return;

        IntStream.range(0, count).forEach(i -> {
            Notification notification = new Notification();
            notification.setMessage(faker.lorem().sentence());
            notification.setUser(users.get(random.nextInt(users.size())));
            notification.setEvent(events.get(random.nextInt(events.size())));
            notificationRepository.save(notification);
        });
    }
}
