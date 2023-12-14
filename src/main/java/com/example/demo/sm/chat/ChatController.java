package com.example.demo.sm.chat;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ChatController {
	
	private final ChatService chatService;
	
	public ChatController(ChatService chatService) {
		this.chatService = chatService;
	}
	
	@GetMapping("/roomset")
	public String roomSet() {
		
		return "sm/roomset";
	}
	
	@GetMapping("/connect_chatroom")
	public String creatRoom(@RequestParam("session_id") String sessionId, @RequestParam("follower_id") String followerId) {
		
		String room_name = sessionId + "#" + followerId;
		chatService.create(room_name);
		
		return "sm/chat_test";
	}
}
