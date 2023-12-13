package com.example.demo.dy.chat;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChatController {
	
	@GetMapping("/chat")
	public String chat() {
		return "dw_view/21_Chat";
			
	}

}
