package com.jibberJabberFollowers.service;

import com.jibberJabberFollowers.dto.FollowCreatorDto;
import com.jibberJabberFollowers.dto.FollowDto;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface FollowService {
    FollowDto follow(UUID userId);

    Page<FollowDto> getFollowers(UUID userId, int page, int size);

    Page<FollowDto> getFollowing(UUID userId, int page, int size);

    void unfollow(UUID userId, UUID followingId);

}
