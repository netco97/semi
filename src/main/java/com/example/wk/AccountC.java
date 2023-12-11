package com.example.wk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccountC {
	
	@Autowired
	
	
	@GetMapping("artist_reg")
	public String artist_reg() {
		return "artist_reg";
	}
	
	@GetMapping("artist_update")
	public String artist_update() {
		return "artist_update";
	}
	
	@GetMapping("artist_detail_viewer")
	public String artist_detail_viewer() {
	    return "artist_detail_viewer";
	}
	
	@GetMapping("artist_detail_owner")
	public String artist_detail_owner() {
		return "artist_detail_owner";
	}
	
	@GetMapping("/music_upload")
	public String music_upload() {
		return "music_upload";
	}
	
	@GetMapping("/mypage_artist")
	public String mypage_artist() {
		return "mypage_artist";
	}
	
	@GetMapping("/mypage_user")
	public String mypage_user() {
		return "mypage_user";
	}
	

}


