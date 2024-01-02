package com.example.demo.sm.chatlist;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatListService {
	private final ChatListMapper chatListMapper;

	public ArrayList<ChatListDTO> getRoomIds(String cur_userNickname) {
		// TODO Auto-generated method stub
		return chatListMapper.getRoomIds(cur_userNickname);
	}

}
