package com.example.demo.th.musicsearch;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	  public String musicMenu(Model model) {
        
        // TagsDTO 는 이미 각각의 배열임;
        // mybatis에서 매핑하면 그값을 dto의 배열에 저장함
        // 그러면 TagsDTO가 사실 끝임
        // 이걸 List로 만드는 필요가 없음
        
        
        TagsDTO tags = new TagsDTO();
        tags = (TagsDTO) musicService.getTags();
        System.out.println("c test" + tags);
        model.addAttribute("tags", tags);
        
        return "th/musicMenu";
    }

	@GetMapping("/musicDetail")
	public String musicDetail(@RequestParam("song_id") int song_id,Model model) {
		
		List<MusicCommentDTOwithNickName> getComments = new ArrayList<>();
		getComments = musicCommentService.getComments(song_id);
		
		
		System.out.println("겟 커멘츠 ! : " +getComments);
		model.addAttribute( "comments",getComments);
		
		return "th/musicDetail";
	}
}
