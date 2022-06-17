package com.jibberJabberFollowers.service.impl;

import com.jibberJabberFollowers.dto.FollowDto;
import com.jibberJabberFollowers.model.Follow;
import com.jibberJabberFollowers.repository.FollowRepository;
import com.jibberJabberFollowers.service.FollowService;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.representations.AccessToken;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.logging.Logger;

@Service
public class FollowServiceImpl implements FollowService {

    private final FollowRepository followRepository;
    private final Logger logger = Logger.getLogger(FollowServiceImpl.class.getName());

    public FollowServiceImpl(FollowRepository followRepository) {
        this.followRepository = followRepository;
    }

    @Override
    public FollowDto follow(UUID userId) {
        Follow follow = Follow.builder()
                .followedById(getUserData())
                .followingId(userId)
                .build();
        logger.info("New Follow Started");
        follow = followRepository.save(follow);
        logger.info("User " + follow.getFollowedById() + " is now following " + follow.getFollowingId());
        return FollowDto.from(follow);
    }

    private UUID getUserData() {
        KeycloakPrincipal principal = (KeycloakPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        KeycloakSecurityContext context = (KeycloakSecurityContext) principal.getKeycloakSecurityContext();
        AccessToken token = context.getToken();
        return UUID.fromString(token.getPreferredUsername());
    }

    @Override
    public Page<FollowDto> getFollowers(UUID userId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Follow> follows = followRepository.findByFollowingId(userId, pageable);
        return follows.map(FollowDto::from);
    }

    @Override
    public Page<FollowDto> getFollowing(UUID userId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Follow> follows = followRepository.findByFollowedById(userId, pageable);
        return follows.map(FollowDto::from);
    }

    @Override
    public void unfollow(UUID userId, UUID followingId) {
        logger.info("New Unfollow Started");
        Follow follow = followRepository.findByFollowedByIdAndFollowingId(userId, followingId).orElseThrow(() -> new IllegalArgumentException("No follow found"));
        followRepository.delete(follow);
        logger.info("User " + userId + " is no longer following " + followingId);
    }
}
