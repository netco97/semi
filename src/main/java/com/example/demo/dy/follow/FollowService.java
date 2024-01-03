	package com.example.demo.dy.follow;
	
	
	import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Service;
	import org.springframework.transaction.annotation.Transactional;
	
	@Service
	@Transactional
	public class FollowService {
	
	    @Autowired
	    private FollowMapper followMapper;
	    
	    public boolean existsFollow(String followerUserId, String targetUserId) {
	        return followMapper.existsFollow(followerUserId, targetUserId);
	    }
	
	    
	    public boolean toggleFollow(String followerUserId, String targetUserId) {
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

	        // 팔로우 상태를 토글한 후에 현재 상태 반환
	        return isFollowing;
	    }
	    
	    public List<FollowDTO> getFollowerList(String userId) {
	        return followMapper.getFollowerList(userId);
	    }
	    
	    public List<FollowDTO> getFollowerListWithInfo(String userId, int start, int end) {
	        return followMapper.getFollowerListWithInfo(userId, start, end);
	    }
	    
	}