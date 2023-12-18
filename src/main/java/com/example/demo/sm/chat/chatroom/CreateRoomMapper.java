package com.example.demo.sm.chat.chatroom;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CreateRoomMapper {
	int regChatRooms(CreateRoomDTO createRoomDTO);
	Integer dupliCheck(@Param("user1") String user1, @Param("user2") String user2);
	CreateRoomDTO select_roomID(@Param("user1") String user1, @Param("user2") String user2);

}
