package com.jibberJabberFollowers.repository;

import com.jibberJabberFollowers.model.Follow;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface FollowRepository extends JpaRepository<Follow, UUID> {

    Page<Follow> findByFollowedById(UUID userId, Pageable pageable);

    Page<Follow> findByFollowingId(UUID userId, Pageable pageable);

    Optional<Follow> findByFollowedByIdAndFollowingId(UUID userId, UUID followingId);
}
