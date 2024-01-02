package com.example.demo.sm.chatlist;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ChatListMapper {

	ArrayList<ChatListDTO> getRoomIds(@Param("cur_userNickname") String cur_userNickname);

	ChatListDTO getChatUserInfo(@Param("roomId") String roomId);

	String getSenderInfo(@Param("cur_userNickname") String cur_userNickname, @Param("roomId") String roomId);

}
