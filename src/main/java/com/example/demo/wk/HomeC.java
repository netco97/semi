package com.example.demo.wk;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.th.music_comment.MusicCommentService;
import com.example.demo.th.musicsearch.MusicService;
import com.example.demo.th.musicsearch.TagsDTO;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HomeC {

	private final MusicService musicService;
	private final ComposerService composerService;

	@GetMapping("/")
	public String home(Model model) {
		// 중간 페이지 내용을 설정
		List<ComposerDTO> artistList = composerService.getAllArtists();
		TagsDTO tags = (TagsDTO) musicService.getTags();
		model.addAttribute("content", "wk/home");
		model.addAttribute("tags", tags);
		model.addAttribute("artists", artistList);

		return "wk/index";
	}

	@GetMapping("/login")
	public String login(Model model) {

		model.addAttribute("content", "dw_view/04_Login");
		return "wk/index";
	}

}
