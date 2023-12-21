package com.example.demo.dy.follow;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/follow")
public class FollowController {
	

    @Autowired
    private FollowService followService;

    @PostMapping("/toggle")
    public void toggleFollow(
            @RequestParam("followerComposerId") Long followerComposerId,
            @RequestParam("targetComposerId") Long targetComposerId) 
    {
        followService.toggleFollow(followerComposerId, targetComposerId);
        
        
    }
}