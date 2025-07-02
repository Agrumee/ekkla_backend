package com.agrumee.backend.repository;

import com.agrumee.backend.model.TagType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TagTypeRepository extends JpaRepository<TagType, Long> {
    Optional<TagType> findByLabel(String label);
}
