package com.agrumee.backend.service.seeders;

import com.agrumee.backend.model.Tag;
import com.agrumee.backend.model.TagType;
import com.agrumee.backend.repository.TagRepository;
import com.agrumee.backend.repository.TagTypeRepository;
import net.datafaker.Faker;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.IntStream;

@Service
public class TagSeeder {

    private final TagRepository tagRepository;
    private final TagTypeRepository tagTypeRepository;
    private final Faker faker = new Faker();

    public TagSeeder(TagRepository tagRepository, TagTypeRepository tagTypeRepository) {
        this.tagRepository = tagRepository;
        this.tagTypeRepository = tagTypeRepository;
    }

    public void seed(int countPerType) {
        Optional<TagType> communityTypeOpt = tagTypeRepository.findByLabel("COMMUNITY");
        Optional<TagType> accessibilityTypeOpt = tagTypeRepository.findByLabel("ACCESSIBILITY");

        if (communityTypeOpt.isEmpty() || accessibilityTypeOpt.isEmpty()) {
            throw new IllegalStateException("Les types de tags COMMUNITY et ACCESSIBILITY doivent être présents en base.");
        }

        TagType communityType = communityTypeOpt.get();
        TagType accessibilityType = accessibilityTypeOpt.get();

        // Seed COMMUNITY tags
        IntStream.range(0, countPerType).forEach(i -> {
            String label = faker.lorem().word() + "_community";
            if (tagRepository.findByLabel(label).isEmpty()) {
                Tag tag = new Tag();
                tag.setLabel(label);
                tag.setDescription("Tag communautaire : " + faker.lorem().sentence());
                tag.setType(communityType);
                tagRepository.save(tag);
            }
        });

        // Seed ACCESSIBILITY tags
        IntStream.range(0, countPerType).forEach(i -> {
            String label = faker.lorem().word() + "_access";
            if (tagRepository.findByLabel(label).isEmpty()) {
                Tag tag = new Tag();
                tag.setLabel(label);
                tag.setDescription("Tag accessibilité : " + faker.lorem().sentence());
                tag.setType(accessibilityType);
                tagRepository.save(tag);
            }
        });
    }
}
