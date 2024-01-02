package com.example.demo.sm.chatlist;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ChatListController {
	private final ChatListService chatListService;
	@GetMapping("/chatlist")
	public String chatlist(Model model, HttpSession session) {
		
		String cur_userNickname = session.getAttribute("userNickname").toString();
		
		ArrayList<ChatListDTO> chat = chatListService.getRoomIds(cur_userNickname);
		for(ChatListDTO c : chat) {
			System.out.println(c);
		}
		
		
		model.addAttribute("content", "sm/chatlist");
		
		return "wk/index";
	}

}
