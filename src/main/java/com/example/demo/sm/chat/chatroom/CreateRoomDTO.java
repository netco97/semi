package com.example.demo.sm.chat.chatroom;

import lombok.Data;

@Data
public class CreateRoomDTO {
	private String roomId;
	private String user1;
	private String user2;
	
	public CreateRoomDTO(String roomId, String user1, String user2) {
		this.roomId = roomId;
		this.user1 = user1;
		this.user2 = user2;
	}
}
