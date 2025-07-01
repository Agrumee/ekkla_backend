package com.agrumee.backend.service.seeders;

import com.agrumee.backend.model.AccessibilityTag;
import com.agrumee.backend.repository.AccessibilityTagRepository;
import net.datafaker.Faker;
import org.springframework.stereotype.Service;

import java.util.stream.IntStream;

@Service
public class AccessibilityTagSeeder {

    private final AccessibilityTagRepository accessibilityTagRepository;
    private final Faker faker = new Faker();

    public AccessibilityTagSeeder(AccessibilityTagRepository accessibilityTagRepository) {
        this.accessibilityTagRepository = accessibilityTagRepository;
    }

    public void seed(int count) {
        IntStream.range(0, count).forEach(i -> {
            AccessibilityTag tag = new AccessibilityTag();
            tag.setName(faker.lorem().word());
            tag.setDescription(faker.lorem().sentence());
            accessibilityTagRepository.save(tag);
        });
    }
}
