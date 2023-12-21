package com.example.demo.th.music_comment;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MusicCommentC {
	
	private final MusicCommentService musicCommentService;
	
	@PostMapping("/RegMusicComment")
	public String regMusicComment(@RequestParam("song_id") int song_id, String inputComment, RedirectAttributes redirectAttributes) {
		
		System.out.println("컨트롤러 송아이디 : :"+ song_id);
		
	
		
		
		
		musicCommentService.regMusicComment(song_id,inputComment);


		redirectAttributes.addAttribute("song_id", song_id);

	     return "redirect:/musicDetail";
	}
}


