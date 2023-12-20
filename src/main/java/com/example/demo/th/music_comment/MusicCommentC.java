package com.example.demo.th.music_comment;

import org.apache.catalina.connector.Request;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MusicCommentC {
	
	private final MusicCommentService musicCommentService;
	
	@PostMapping("/RegMusicComment")
	public String regMusicComment(@RequestParam("song_id") int song_id,String inputComment) {
		
		System.out.println(song_id + inputComment);
		
		
		
//		musicCommentService.regMusicComment();

		return "redirect:/some-path"; // 적절한 리다이렉션 경로를 지정하세요.
	}
}
