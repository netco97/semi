package com.example.demo.th.music_like;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class MusicLikeC {
	private final MusicLikeService musicLikeService;

	public MusicLikeC(MusicLikeService musicLikeService) {
		this.musicLikeService = musicLikeService;

	}

	@GetMapping("/MusicLikeC")
	public List<Integer> MusicLike(@RequestParam("song_id") int song_id) {

		// update
		musicLikeService.checkLike(song_id);

		List<Integer> result = new ArrayList<>();

		// select
		result.add(musicLikeService.selLike(song_id));

		// Like count
		result.add(musicLikeService.checkLikeCount(song_id));

		return result;
	}

	@GetMapping("/MusicLikeCheckC")
	public int MusicLikeCheck(@RequestParam("song_id") int song_id) {

		int result = musicLikeService.loadingCheckLike(song_id);

		System.out.println("뮤직 라이크 체크 : " + song_id);
		System.out.println("뮤직 라이크 체크 : " + result);

		return result;

	}

}
