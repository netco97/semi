package com.example.demo.th.music_like;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class MusicLikeC {
	private final MusicLikeService musicLikeService;
	
	public MusicLikeC(MusicLikeService musicLikeService) {
		this.musicLikeService = musicLikeService;
		
		
	}
	
	@GetMapping("/MusicLikeC")
	public int MusicLike(@RequestParam("song_id") int song_id) {
		
		//update
		musicLikeService.checkLike(song_id);
		
		//select
		int result = musicLikeService.selLike(song_id);
		
		
		
		return result;
	}
	
	@GetMapping("/MusicLikeCheckC")
	public int MusicLikeCheck(@RequestParam("song_id")int song_id) {
		
		int result = musicLikeService.loadingCheckLike(song_id);
		
	
		System.out.println("뮤직 라이크 체크 : " + song_id);
		System.out.println("뮤직 라이크 체크 : " + result);
		
		return result;
		
		
	}
		
		
	
	
	
	
	
}
