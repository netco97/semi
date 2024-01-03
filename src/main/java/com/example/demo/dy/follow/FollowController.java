package com.example.demo.dy.follow;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;

@RestController
@RequestMapping("/follow")
public class FollowController {

	@Autowired
	private FollowService followService;

	@PostMapping("/toggle")
    @ResponseBody
    public Map<String, Object> toggleFollow(
            @RequestParam("followerUserId") String followerUserId,
            @RequestParam("targetUserId") String targetUserId,
            Model model) 
    {
        boolean isFollowing = followService.existsFollow(followerUserId, targetUserId);

        
        Map<String, Object> response = new HashMap<>();
        // 이거 불리언이랑 스트링이랑 같이 담을라면 걍 뒤에 객체로 받아야함
        response.put("isFollowing", isFollowing);
        response.put("followerUserId", followerUserId);
        response.put("targetUserId", targetUserId);

        followService.toggleFollow(followerUserId, targetUserId);

        return response;
    }
	
	
	@GetMapping("/follower_list")
	public List<FollowDTO> showFollowerList(HttpSession httpSession) {
	    String userId = (String) httpSession.getAttribute("userFullPhoneNumber");
	    int start = 1; // 시작 페이지
	    System.out.println("여기임");
	    System.out.println(userId);
	    int end = 10;  // 한 페이지에 표시할 개수
	    List<FollowDTO> followerList = followService.getFollowerListWithInfo(userId, start, end);
	    
	    
	    return followerList;
	}
	
		
}