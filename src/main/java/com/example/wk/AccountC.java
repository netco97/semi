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
	public String artist_reg(Model model) {
		model.addAttribute("content", "wk/artist_reg");
		return "wk/index";
	}
	
	@GetMapping("artist_update")
	public String artist_update(Model model) {
		model.addAttribute("content", "wk/artist_update");
		return "wk/index";
	}
	
	@GetMapping("/music_upload")
	public String music_upload(Model model) {
		model.addAttribute("content", "wk/music_upload");
		return "wk/index";
	}
	
	@GetMapping("/mypage_artist")
	public String mypage_artist(Model model) {
		model.addAttribute("content", "wk/mypage_artist");
		return "wk/index";
	}
	
	@GetMapping("/mypage_user")
	public String mypage_user(Model model) {
		model.addAttribute("content", "wk/mypage_user");
		return "wk/index";
	}
	
}


