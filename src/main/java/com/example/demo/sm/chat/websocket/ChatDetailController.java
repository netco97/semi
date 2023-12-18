package com.example.demo.sm.chat.websocket;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ChatDetailController {
	
	@GetMapping("/room_detail/{roomId}")
	public String chat_detail(@PathVariable String roomId) {
		
		
		return "sm/room_detail";
	}
}
