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
	public CreateRoomDTO creatRoom(@RequestBody RoomSetDTO roomSetDTO) {
		
		
		//변수 세팅
		String user1 = roomSetDTO.getSession_id();
		String user2 = roomSetDTO.getFollower_id();
		ChatRoomDTO chatRoomDTO;
		
		//front에 반환할 DTO
		CreateRoomDTO result;
		
		//중복처리
		if (chatService.dupli_check(user1, user2) > 0) {
		    // 중복된 경우 처리
			CreateRoomDTO roomId = chatService.select_roomId(user1,user2);
			result = roomId;
		}
		
		// DB 저장
		else {
			chatRoomDTO = chatService.create();
			CreateRoomDTO createRoomDTO = new CreateRoomDTO(chatRoomDTO.getRoomId(),user1, user2);
			chatService.save_createroom(createRoomDTO);
			result = createRoomDTO;
		}
		
		
		
		return result;
	}
}
