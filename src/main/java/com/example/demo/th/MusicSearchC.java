package com.example.demo.th;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MusicSearchC {
	private final MusicSearch_Model musicSearch_Model;
	
	
	
	public MusicSearchC(MusicSearch_Model musicSearch_Model) {
		this.musicSearch_Model = musicSearch_Model;
	}

	@GetMapping("/MusicSearchTag")
	public List<SongsDTO> MusicSearchTag (@RequestParam("tags") String tags) {
		
		
		System.out.println(tags);
		List<SongsDTO> result = musicSearch_Model.searchTag(tags);
		
		for(SongsDTO s : result)
		{
			System.out.println(s);
		}
		
		
		return result;
	}
}
