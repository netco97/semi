package com.example.demo.sm.chatlist;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChatListController {
	
	@GetMapping("/chatlist")
	public String chatlist(Model model) {
		
		model.addAttribute("content", "sm/chatlist");
		
		return "wk/index";
	}

}
