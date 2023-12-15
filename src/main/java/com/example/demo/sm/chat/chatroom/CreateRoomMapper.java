package com.example.demo.sm.chat.chatroom;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CreateRoomMapper {
	int regChatRooms(CreateRoomDTO createRoomDTO);
	Integer dupliCheck(@Param("from_user") String from_user, @Param("to_user") String to_user);


}
