package com.example.demo.dy.follow;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class FollowDTO {
    private String followId;
    private String followerUserId;
    private String targetUserId;
    private LocalDateTime created_at;
    private String isfollow;
    private String composer_name;
    private String composer_img;
    
   

   
    public FollowDTO() {
    }

    public FollowDTO(String followId, String followerUserId, String targetUserId,String composer_name,String composer_img) {
        this.followId = followId;
        this.followerUserId = followerUserId;
        this.targetUserId = targetUserId;
       
    }

   
}