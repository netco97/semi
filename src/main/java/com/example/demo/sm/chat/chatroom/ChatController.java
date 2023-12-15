package com.example.demo.sm.chat.chatroom;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ChatController {
	
	private final ChatService chatService;
	
	public ChatController(ChatService chatService) {
		this.chatService = chatService;
	}
	
	@GetMapping("/roomset")
	public String roomSet() {
		
		return "sm/roomset";
	}
	
	@PostMapping("/connect_chatroom")
	@ResponseBody
	public ChatRoomDTO creatRoom(@RequestBody RoomSetDTO roomSetDTO) {
		
		System.out.println(roomSetDTO.getSession_id() + " " + roomSetDTO.getFollower_id());
		
		//변수 세팅
		String from_user = roomSetDTO.getSession_id();
		String to_user = roomSetDTO.getFollower_id();
		ChatRoomDTO result;
		
		//중복처리
		if (chatService.dupli_check(from_user, to_user) > 0) {
		    // 중복된 경우 처리
			result = null;
		}
		
		// DB 저장
		else {
			result = chatService.create();
			CreateRoomDTO createRoomDTO = new CreateRoomDTO(result.getRoomId(),from_user, to_user);
			chatService.save_createroom(createRoomDTO);
		}
		
		
		
		return result;
	}
}
