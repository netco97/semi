package com.example.demo.sm.chat.chatroom;

import java.util.UUID;

import lombok.Data;

@Data
public class ChatRoomDTO{
	
	private String roomId;
	
	public static ChatRoomDTO create() {
		ChatRoomDTO room = new ChatRoomDTO();
		room.roomId = UUID.randomUUID().toString();
		
		return room;
	}
}
