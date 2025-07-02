package com.agrumee.backend.repository;

import com.agrumee.backend.model.EventType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EventTypeRepository extends JpaRepository<EventType, Long> {
    Optional<EventType> findByLabel(String label);
}
