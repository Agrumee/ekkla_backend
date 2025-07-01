package com.agrumee.backend.service.seeders;

import com.agrumee.backend.model.Place;
import com.agrumee.backend.repository.PlaceRepository;
import net.datafaker.Faker;
import org.springframework.stereotype.Service;

import java.util.stream.IntStream;

@Service
public class PlaceSeeder {

    private final PlaceRepository placeRepository;
    private final Faker faker = new Faker();

    public PlaceSeeder(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    public void seed(int count) {
        IntStream.range(0, count).forEach(i -> {
            Place place = new Place();
            place.setAddress(faker.address().fullAddress());
            place.setLatitude(Double.parseDouble(faker.address().latitude()));
            place.setLongitude(Double.parseDouble(faker.address().longitude()));
            placeRepository.save(place);
        });
    }
}
