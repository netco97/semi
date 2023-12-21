package com.example.demo.sm.chat.chatroom;

import lombok.Data;

@Data
public class CreateRoomDTO {
	private String room_id;
	private String user1;
	private String user2;
	
	public CreateRoomDTO(String room_id, String user1, String user2) {
		this.room_id = room_id;
		this.user1 = user1;
		this.user2 = user2;
	}
}
