package com.example.demo.sm.chat;

import java.util.UUID;

import lombok.Data;

@Data
public class ChatRoomDTO{
	
	private String roomId;
	private String name;
	
	public static ChatRoomDTO create(String name) {
		ChatRoomDTO room = new ChatRoomDTO();
		
		room.roomId = UUID.randomUUID().toString();
		room.name = name;
		return room;
	}
}
