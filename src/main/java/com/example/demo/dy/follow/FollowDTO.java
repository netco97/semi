package com.example.demo.dy.follow;

import lombok.Data;

@Data
public class FollowDTO {
    private Long followTableId;
    private Long followerComposerId;
    private Long targetComposerId;

   
    public FollowDTO() {
    }

    public FollowDTO(Long id, Long followerComposerId, Long targetComposerId) {
        this.followTableId = followTableId;
        this.followerComposerId = followerComposerId;
        this.targetComposerId = targetComposerId;
    }

   
}