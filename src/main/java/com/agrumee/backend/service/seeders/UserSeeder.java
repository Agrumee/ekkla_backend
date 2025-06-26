package com.agrumee.backend.service.seeders;

import com.agrumee.backend.model.User;
import com.agrumee.backend.repository.UserRepository;
import net.datafaker.Faker;
import org.springframework.stereotype.Service;

import java.util.stream.IntStream;

@Service
public class UserSeeder {

    private final UserRepository userRepository;
    private final Faker faker = new Faker();

    public UserSeeder(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void seed(int count) {
        IntStream.range(0, count).forEach(i -> {
            User user = new User();
            user.setPseudo(faker.internet().username());
            user.setEmail(faker.internet().emailAddress());
            user.setPassword(faker.internet().password());
            user.setProfilePicture(faker.avatar().image());
            user.setBirthDate(faker.date().birthday().toLocalDateTime().toLocalDate());
            user.setBio(faker.lorem().sentence());
            user.setJob(faker.job().title());
            user.setPronouns(faker.demographic().sex());

            userRepository.save(user);
        });
    }


}
