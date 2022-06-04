package com.jibberJabberFollowers.dto;

import com.jibberJabberFollowers.model.Follow;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class FollowDto {

    private UUID id;

    private UUID followerId;

    private UUID followingId;

    public static FollowDto from(Follow follow) {
        return FollowDto.builder()
                .id(follow.getId())
                .followerId(follow.getFollowedById())
                .followingId(follow.getFollowingId())
                .build();
    }
}
