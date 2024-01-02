	package com.example.demo.dy.follow;
	
	
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Service;
	import org.springframework.transaction.annotation.Transactional;
	
	@Service
	@Transactional
	public class FollowService {
	
	    @Autowired
	    private FollowMapper followMapper;
	
	    
	    public void toggleFollow(String followerUserId, String targetUserId) {
	        boolean isFollowing = followMapper.existsFollow(followerUserId, targetUserId);
	        System.out.println("이거 찎힘 ");
	        
	        if (isFollowing) {
	            FollowDTO follow = new FollowDTO();
	            follow.setFollowerUserId(followerUserId);
	            follow.setTargetUserId(targetUserId);
	            followMapper.deleteFollow(follow);
	            System.out.println("언팔했어용");
	        } else {
	            FollowDTO follow = new FollowDTO();
	            follow.setFollowerUserId(followerUserId);
	            follow.setTargetUserId(targetUserId);
	            
	            System.out.println("DTO 내용 확인" + follow);
	            followMapper.insertFollow(follow);
	        }
	    }
	}