package com.example.demo.sm.chat.chatroom;

import lombok.Data;

@Data
public class CreateRoomDTO {
	private String room_id;
	private String from_user;
	private String to_user;
	
	public CreateRoomDTO(String room_id, String from_user, String to_user) {
		this.room_id = room_id;
		this.from_user = from_user;
		this.to_user = to_user;
	}
}
