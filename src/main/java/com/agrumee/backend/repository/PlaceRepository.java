package com.agrumee.backend.repository;

import com.agrumee.backend.model.Place;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceRepository extends JpaRepository<Place, Long> {
}
