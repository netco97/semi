
package com.example.demo.th.musicsearch;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.th.music_comment.MusicCommentDTO;
import com.example.demo.th.music_comment.MusicCommentDTOwithNickName;
import com.example.demo.th.music_comment.MusicCommentService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MusicC {

	private final MusicService musicService;
	private final MusicCommentService musicCommentService;
	
	
	@GetMapping("/musicMenu")
	public ModelAndView musicMenu() {
	    TagsDTO tags = (TagsDTO) musicService.getTags();
	    System.out.println("c test" + tags);

	    ModelAndView modelAndView = new ModelAndView("wk/index");
	    modelAndView.addObject("tags", tags);
	    modelAndView.addObject("content", "th/musicMenu");

	    return modelAndView;
	}

	@PostMapping("/musicDetail")
	public String musicDetail(@RequestParam("song_id") int song_id,Model model) {
		
		List<MusicCommentDTOwithNickName> getComments = new ArrayList<>();
		getComments = musicCommentService.getComments(song_id);
		
		
		System.out.println("겟 커멘츠 ! : " +getComments);
		System.out.println("유저 닉네임 왜 안나옴 : " +getComments.get(0).getUser_nickName());
		model.addAttribute("comments",getComments);
		model.addAttribute("content", "th/musicDetail");
		
		return "wk/index";
	}
	

	
}
