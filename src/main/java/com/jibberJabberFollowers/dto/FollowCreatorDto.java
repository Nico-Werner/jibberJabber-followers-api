package com.jibberJabberFollowers.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class FollowCreatorDto {

    private UUID followerId;

    private UUID followingId;

}
