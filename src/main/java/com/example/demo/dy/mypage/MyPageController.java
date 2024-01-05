package com.example.demo.dy.mypage;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MyPageController {
	private final MyPageService myPageService;
	

	
	@GetMapping("/mypage")
	public String mypage(Model model) {
		
		
		
		
		model.addAttribute("content", "wk/mypage_user");
        return "wk/index";
	}
	
	@GetMapping("/deleteUser")
	public String deleteUser(HttpSession httpsession) {
		
		String myPK =  httpsession.getAttribute("userFullPhoneNumber").toString();
		
		System.out.println("myPK test " + myPK);
		
		String SK= myPageService.deleteUser(myPK);
		
		if(SK.equals("sucesss")) {
			
			httpsession.invalidate(); 
			
            return "redirect:/";
		}else {
			
			 System.out.println("오류오류오류");
		} 
		
	
		return "redirect:/";
		
		
		
	}
	
	
	
	
	
	


}
