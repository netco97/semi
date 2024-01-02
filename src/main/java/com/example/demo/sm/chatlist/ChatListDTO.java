package com.example.demo.sm.chatlist;

import lombok.Data;

@Data
public class ChatListDTO {
	private String roomId;
	private String sender;
	private String message;
	private String cur_date;

}