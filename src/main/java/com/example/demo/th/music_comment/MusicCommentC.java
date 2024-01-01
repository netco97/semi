package com.example.demo.th.music_comment;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MusicCommentC {

	private final MusicCommentService musicCommentService;

	@GetMapping("/RegMusicComment")
	public String regMusicComment(@RequestParam("song_id") int song_id, @RequestParam("comment_text") String comment_text, RedirectAttributes redirectAttributes) {

		System.out.println("컨트롤러 송아이디 : " + song_id);
		System.out.println("컨트롤러 커멘트 : " + comment_text);

		musicCommentService.regMusicComment(song_id, comment_text);
		
		

		redirectAttributes.addAttribute("song_id", song_id);

		return "redirect:/musicDetail";
	}
	
	@GetMapping("/DeleteMusicComment")
	public String DeleteMusicComment(@RequestParam("comment_id") int comment_id) {
		
		
		System.out.println("딜리트 ::: " +comment_id);
		
		musicCommentService.deleteMusicComment(comment_id);
	
		return "th/musicDetail";
	}
	
	
}
