package com.example.demo.sm.chat.chatroom;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

@Service
public class ChatService {
    
    private final CreateRoomMapper createRoomMapper;
    
    public ChatService(CreateRoomMapper createRoomMapper) {
		this.createRoomMapper = createRoomMapper;
	}
    
    public ChatRoomDTO create() {
    	
       ChatRoomDTO chatRoomDTO = ChatRoomDTO.create();
            
       return chatRoomDTO;
    }
    
    public void save_createroom(CreateRoomDTO createRoomDTO) {
    	if(createRoomMapper.regChatRooms(createRoomDTO)==1) {
    		System.out.println("createRoom 등록 성공");
    	}
    	else {
    		System.out.println("등록 실패");
    	}
    }
    
    public Integer dupli_check(String from_user, String to_user) {
    	return createRoomMapper.dupliCheck(from_user, to_user);
    }
}