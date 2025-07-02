package com.agrumee.backend.repository;

import com.agrumee.backend.model.Tag;
import com.agrumee.backend.model.TagType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long> {

    List<Tag> findByType(TagType type);

    Optional<Tag> findByLabel(String label);
}
