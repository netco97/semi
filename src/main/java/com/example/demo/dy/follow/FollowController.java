package com.example.demo.dy.follow;

import java.util.HashMap;
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
	public String showFollowerList(Model model, HttpSession session) {
	    // 세션에서 현재 사용자의 ID를 가져오는 코드
	    String userId = (String) session.getAttribute("userFullPhoneNumber");

	    // 팔로워 리스트를 가져와서 모델에 추가
	    List<FollowDTO> followerList = followService.getFollowerList(userId);
	    model.addAttribute("followerList", followerList);

	    return "follower_list_modal"; // 모달창을 띄울 HTML 페이지 이름
	}
	
	
	
}