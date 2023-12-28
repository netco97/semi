package com.example.demo.dy.mypage;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyPageController {
	
	@GetMapping("/artistmypage")
	public String artistmypage(Model model) {
	
		
		
		
		model.addAttribute("content", "wk/mypage_artist");
        return "wk/index";
	}
	
	@GetMapping("/mypage")
	public String mypage(Model model) {
		
		
		
		
		model.addAttribute("content", "wk/mypage_user");
        return "wk/index";
	}
	
	
	
	
	
	


}
