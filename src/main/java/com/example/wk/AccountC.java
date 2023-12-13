package com.example.wk;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class AccountC {
	
	@Autowired
	AccountService accountService;
    
	@GetMapping("/getAccountAll")
	public String accountAll() {
		List<AccountDTO> a = accountService.getAccountAll();
		for (AccountDTO accountDTO : a) {
			System.out.println(accountDTO);
		}
		return "wk/index";
	}
	
//	@GetMapping("/artist_detail")
//	public String artist_detail_owner() {
//		return "wk/artist_detail";
//	}
	
	@GetMapping("artist_reg")
	public String artist_reg() {
		return "wk/artist_reg";
	}
	
	@GetMapping("artist_update")
	public String artist_update() {
		return "wk/artist_update";
	}
	
	@GetMapping("/music_upload")
	public String music_upload() {
		return "wk/music_upload";
	}
	
	@GetMapping("/mypage_artist")
	public String mypage_artist() {
		return "wk/mypage_artist";
	}
	
	@GetMapping("/mypage_user")
	public String mypage_user() {
		return "wk/mypage_user";
	}
	
}


