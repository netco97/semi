package com.example.demo.dy.follow;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FollowService {

    @Autowired
    private FollowMapper followMapper;

    @Transactional
    public void toggleFollow(Long followerComposerId, Long targetComposerId) {
        boolean isFollowing = followMapper.existsFollow(followerComposerId, targetComposerId);

        if (isFollowing) {
            FollowDTO follow = new FollowDTO();
            follow.setFollowerComposerId(followerComposerId);
            follow.setTargetComposerId(targetComposerId);
            followMapper.deleteFollow(follow);
        } else {
            FollowDTO follow = new FollowDTO();
            follow.setFollowerComposerId(followerComposerId);
            follow.setTargetComposerId(targetComposerId);
            followMapper.insertFollow(follow);
        }
    }
}