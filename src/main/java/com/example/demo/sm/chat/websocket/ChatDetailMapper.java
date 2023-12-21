package com.example.demo.sm.chat.websocket;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ChatDetailMapper {
	int regChat(ChatMessageDTO chatMessageDTO);
	
	List<ChatMessageDTO> get_chatList(@Param("roomId") String roomId);
}
