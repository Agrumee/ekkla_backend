package com.agrumee.backend.repository;

import com.agrumee.backend.model.Participation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipationRepository extends JpaRepository<Participation, Long> {
    int countByEventIdAndIsConfirmedTrue(Long eventId);
}
