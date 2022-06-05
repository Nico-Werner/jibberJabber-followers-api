package com.jibberJabberFollowers.service.impl;

import com.jibberJabberFollowers.dto.FollowCreatorDto;
import com.jibberJabberFollowers.dto.FollowDto;
import com.jibberJabberFollowers.model.Follow;
import com.jibberJabberFollowers.repository.FollowRepository;
import com.jibberJabberFollowers.service.FollowService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FollowServiceImpl implements FollowService {

    private final FollowRepository followRepository;

    public FollowServiceImpl(FollowRepository followRepository) {
        this.followRepository = followRepository;
    }

    @Override
    public FollowDto follow(FollowCreatorDto followCreatorDto) {
        Follow follow = Follow.builder()
                .followedById(followCreatorDto.getFollowerId())
                .followingId(followCreatorDto.getFollowingId())
                .build();
        follow = followRepository.save(follow);
        return FollowDto.from(follow);
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
        Follow follow = followRepository.findByFollowedByIdAndFollowingId(userId, followingId).orElseThrow(() -> new IllegalArgumentException("No follow found"));
        followRepository.delete(follow);
    }
}
