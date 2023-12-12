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
	public List<SongsDTO> MusicSearchTag(@RequestParam("genre") String genre,@RequestParam("mood") String mood,@RequestParam("instrument") String instrument ) {
		
	
		System.out.println("장르 : "+ genre);
		System.out.println("분위기 : " + mood);
		System.out.println("악기 : "+ instrument);
		
		
//		  List<SongsDTO> result = musicSearch_Model.MusicSearchTag(); // String
//		  genre = ""; 
//		  
//		 
//		  for(SongsDTO s : result) { // System.out.println(s); // }
		 		return null;
//	}
		  
}
	}
