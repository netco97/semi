package com.example.demo.sm.chat.websocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ChatDetailController {
	private final SimpMessageSendingOperations messagingTemplate;
	
	@MessageMapping("/chat")
	public void send(ChatMessageDTO chatMessageDTO) {
		System.out.println("sender 확인" + chatMessageDTO.getSender());
		System.out.println("message 확인 " + chatMessageDTO.getMessage());
		System.out.println("roomId 확인" + chatMessageDTO.getRoomId());
		
		messagingTemplate.convertAndSend("/sub/topic/"+ chatMessageDTO.getRoomId(), chatMessageDTO);
	}
}
