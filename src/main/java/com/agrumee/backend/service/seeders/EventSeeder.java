package com.agrumee.backend.service.seeders;

import com.agrumee.backend.model.*;
import com.agrumee.backend.repository.*;
import net.datafaker.Faker;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class EventSeeder {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final PlaceRepository placeRepository;
    private final CommunityTagRepository communityTagRepository;
    private final AccessibilityTagRepository accessibilityTagRepository;

    private final Faker faker = new Faker();
    private final Random random = new Random();

    private <T> Set<T> pickRandomSubset(List<T> list, int maxCount) {
        if (maxCount <= 0 || list.isEmpty()) {
            return Collections.emptySet();
        }

        int bound = Math.min(maxCount, list.size());
        int count = random.nextInt(bound) + 1;

        Collections.shuffle(list);
        return list.stream()
                .limit(count)
                .collect(Collectors.toSet());
    }


    public EventSeeder(EventRepository eventRepository,
                       UserRepository userRepository,
                       PlaceRepository placeRepository,
                       CommunityTagRepository communityTagRepository,
                       AccessibilityTagRepository accessibilityTagRepository) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
        this.placeRepository = placeRepository;
        this.communityTagRepository = communityTagRepository;
        this.accessibilityTagRepository = accessibilityTagRepository;
    }

    public void seed(int count) {
        List<User> users = userRepository.findAll();
        List<Place> places = placeRepository.findAll();
        List<CommunityTag> communityTags = communityTagRepository.findAll();
        List<AccessibilityTag> accessibilityTags = accessibilityTagRepository.findAll();

        if (users.isEmpty() || places.isEmpty()) {
            System.out.println("❌ Cannot seed events: users or places are empty.");
            return;
        }

        IntStream.range(0, count).forEach(i -> {
            Event event = new Event();
            event.setName(faker.book().title());

            event.setDate(LocalDateTime.now().plusDays(random.nextInt(60)));

            // Descriptions de longueur variable (entre 1 et 5 paragraphes)
            int paragraphs = 1 + random.nextInt(5);
            event.setDescription(faker.lorem().paragraph(paragraphs));

            event.setImageUrl(faker.internet().image());
            event.setMinParticipants(5 + random.nextInt(10));
            event.setMaxParticipants(event.getMinParticipants() + random.nextInt(20));

            BigDecimal minPrice = BigDecimal.valueOf(5 + random.nextInt(15));
            BigDecimal maxPrice = minPrice.add(BigDecimal.valueOf(random.nextInt(20)));
            event.setPriceMin(minPrice);
            event.setPriceMax(maxPrice);

            event.setOrganizedBy(users.get(random.nextInt(users.size())));
            event.setPlace(places.get(random.nextInt(places.size())));

            // Remplacement de faker.collection(...).maxLen(3)
            event.setCommunityTags(pickRandomSubset(communityTags, 3));
            event.setAccessibilityTags(pickRandomSubset(accessibilityTags, 2));

            eventRepository.save(event);
        });
    }
}
