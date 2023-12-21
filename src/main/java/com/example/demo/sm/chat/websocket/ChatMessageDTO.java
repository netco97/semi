package com.example.demo.sm.chat.websocket;

import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.Data;

@Data
public class ChatMessageDTO {
	private int chat_id;
	private String roomId; // DB도 일치시켜주려고 roomId로 만듬
	private String sender;
	private String message;
	private String cur_date;

}
