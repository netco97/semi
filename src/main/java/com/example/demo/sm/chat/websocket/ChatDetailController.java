package com.example.demo.sm.chat.websocket;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ChatDetailController {
	private final SimpMessageSendingOperations messagingTemplate;
	private final ChatDetailService chatDetailService;
	
	@MessageMapping("/chat")
	public void send(ChatMessageDTO chatMessageDTO) {
		System.out.println("sender 확인" + chatMessageDTO.getSender());
		System.out.println("message 확인 " + chatMessageDTO.getMessage());
		System.out.println("roomId 확인" + chatMessageDTO.getRoomId());
		
		// 시간 세팅
		chatMessageDTO.setCur_date(cur_Time());
		
		// DB 저장
		chatDetailService.regChat(chatMessageDTO);
		
		
		
		messagingTemplate.convertAndSend("/sub/topic/"+ chatMessageDTO.getRoomId(), chatMessageDTO);
	}
	
	@GetMapping("/get_chat_list")
	@ResponseBody
	public List<ChatMessageDTO> get_chatroom(@RequestParam String roomId) {
		List<ChatMessageDTO> result = chatDetailService.get_chatList(roomId);
		for(ChatMessageDTO chatMessageDTO : result) {
			System.out.println(chatMessageDTO);
		}
		
		Collections.reverse(result);
		
		return result;
	}
	
	public String cur_Time() {
			
			// 현재 시간 받아오기
			LocalDateTime currentTime = LocalDateTime.now();
	
	        // 원하는 형식으로 포맷팅
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	        String formattedTime = currentTime.format(formatter);
	        
	        return formattedTime;
		}
}
