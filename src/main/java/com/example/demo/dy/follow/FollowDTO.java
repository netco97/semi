package com.example.demo.dy.follow;

import lombok.Data;

@Data
public class FollowDTO {
    private Long followId;
    private Long followerComposerId;
    private Long targetComposerId;

   
    public FollowDTO() {
    }

    public FollowDTO(Long followId, Long followerComposerId, Long targetComposerId) {
        this.followId = followId;
        this.followerComposerId = followerComposerId;
        this.targetComposerId = targetComposerId;
    }

   
}