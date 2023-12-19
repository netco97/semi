package com.example.demo.sm.chat.websocket;

import lombok.Data;

@Data
public class ChatMessageDTO {
	private String roomId;
	private String sender;
	private String message;
}
