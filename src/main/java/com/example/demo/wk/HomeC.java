package com.example.demo.wk;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeC {

    @GetMapping("/")
    public String home(Model model) {
        // 중간 페이지 내용을 설정
        model.addAttribute("content", "wk/home");
        return "wk/index";
    }
    
	@GetMapping("/login")
	public String login(Model model) {
		
		model.addAttribute("content", "dw_view/04_Login");
		return "wk/index";
	}
		
}
