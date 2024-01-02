package com.example.demo.sm.chatlist;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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
		ChatListDTO temp;
		
		//null 값 처리
		for (ChatListDTO c : chat) {
		    temp = chatListService.getChatUserInfo(c.getRoomId());
		    if (temp != null) {
		        c.setSender(temp.getSender() != null ? chatListService.getSenderInfo(cur_userNickname, c.getRoomId()) : cur_userNickname);
		        c.setMessage(temp.getMessage() != null ? temp.getMessage() : "");
		        c.setCur_date(temp.getCur_date() != null ? temp.getCur_date() : "");
		    } else {
		        // temp가 null인 경우에 대한 처리
		        c.setSender(cur_userNickname);
		        c.setMessage("");
		        c.setCur_date("");
		    }
		    System.out.println("최종 DTO List test" + c);
		}
		
		Collections.sort(chat, Comparator.comparing(ChatListDTO::getCur_date).reversed());
		
		model.addAttribute("chatlist", chat);
		model.addAttribute("content", "sm/chatlist");
		
		return "wk/index";
	}

}
