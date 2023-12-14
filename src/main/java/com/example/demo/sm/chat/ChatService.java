package com.example.demo.sm.chat;

import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class ChatService {
	
	private Map<String, ChatRoomDTO> chatRoomMap = null;
	
	public ChatRoomDTO create(String name) {
		ChatRoomDTO chatRoomDTO = ChatRoomDTO.create(name);
		
		chatRoomMap.put(chatRoomDTO.getRoomId(), chatRoomDTO);
		System.out.println("chatRoomDTO 확인 "+ chatRoomDTO.getName()  + "chatRoomMap : " + chatRoomMap);
		
		return chatRoomDTO;
	}

}
