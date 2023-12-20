package com.example.demo.sm.chat.websocket;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatDetailService {
	private final ChatDetailMapper chatDetailMapper;

	public void regChat(ChatMessageDTO chatMessageDTO) {
		if(chatDetailMapper.regChat(chatMessageDTO)==1) {
			System.out.println("chat table 등록 성공");
		}
	}
	
	public List<ChatMessageDTO> get_chatList(String roomId){
		
		return chatDetailMapper.get_chatList(roomId);
	}
	
	
	
}
