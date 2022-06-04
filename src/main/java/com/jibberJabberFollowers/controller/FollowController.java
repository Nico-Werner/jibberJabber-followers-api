package com.jibberJabberFollowers.controller;

import com.jibberJabberFollowers.dto.FollowCreatorDto;
import com.jibberJabberFollowers.dto.FollowDto;
import com.jibberJabberFollowers.service.FollowService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/follow")
public class FollowController {

    private final FollowService followService;

    public FollowController(FollowService followService) {
        this.followService = followService;
    }

    @PostMapping("")
    public FollowDto follow(@RequestBody FollowCreatorDto followCreatorDto) {
        return followService.follow(followCreatorDto);
    }

    @GetMapping("followers/{userId}")
    public Page<FollowDto> getFollowers(@Valid @PathVariable UUID userId,
                                        @RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "10") int size) {
        return followService.getFollowers(userId, page, size);
    }

    @GetMapping("following/{userId}")
    public Page<FollowDto> getFollowing(@Valid @PathVariable UUID userId,
                                        @RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "10") int size) {
        return followService.getFollowing(userId, page, size);
    }

    @DeleteMapping("user/{userId}/unfollow/{followingId}")
    public void unfollow(@Valid @PathVariable UUID userId, @Valid @PathVariable UUID followingId) {
        followService.unfollow(userId, followingId);
    }
}
