package com.example.demo.th;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MusicC {
	

	
	@GetMapping("/musicMenu")
	public String musicMenu() {
		return "th/musicMenu";
	}
	@GetMapping("/musicDetail")
	public String musicDetail() {
		return "th/musicDetail";
	}
}
