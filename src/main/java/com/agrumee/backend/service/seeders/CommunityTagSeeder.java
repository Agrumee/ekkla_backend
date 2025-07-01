package com.agrumee.backend.service.seeders;

import com.agrumee.backend.model.CommunityTag;
import com.agrumee.backend.repository.CommunityTagRepository;
import net.datafaker.Faker;
import org.springframework.stereotype.Service;

import java.util.stream.IntStream;

@Service
public class CommunityTagSeeder {

    private final CommunityTagRepository communityTagRepository;
    private final Faker faker = new Faker();

    public CommunityTagSeeder(CommunityTagRepository communityTagRepository) {
        this.communityTagRepository = communityTagRepository;
    }

    public void seed(int count) {
        IntStream.range(0, count).forEach(i -> {
            CommunityTag tag = new CommunityTag();
            tag.setName(faker.lorem().word());
            tag.setDescription(faker.lorem().sentence());
            communityTagRepository.save(tag);
        });
    }
}
