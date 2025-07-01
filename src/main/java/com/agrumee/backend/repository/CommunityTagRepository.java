package com.agrumee.backend.repository;

import com.agrumee.backend.model.CommunityTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityTagRepository extends JpaRepository<CommunityTag, Long> {
}
