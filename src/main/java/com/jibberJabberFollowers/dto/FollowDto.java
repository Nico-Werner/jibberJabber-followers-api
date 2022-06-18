package com.jibberJabberFollowers.dto;

import com.jibberJabberFollowers.model.Follow;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class FollowDto {

    private UUID id;

    private String followerId;

    private UUID followingId;

    public static FollowDto from(Follow follow) {
        return FollowDto.builder()
                .id(follow.getId())
                .followerId(follow.getFollowerUserName())
                .followingId(follow.getFollowingId())
                .build();
    }
}
