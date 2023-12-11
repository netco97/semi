package com.example.demo.music;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MusicC {
	
	@Autowired
	MusicServic_Model musicServic_Model;

	
	@GetMapping("/musicMenu")
	public String musicMenu() {
		return "th/musicMenu";
	}
	@GetMapping("/musicDetail")
	public String musicDetail() {
		return "th/musicDetail";
	}
}
