package com.example.demo.dy.login.google;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/login/oauth2", produces = "application/json")
public class GoogleController {
	private final GoogleLoginService googleLoginService;
	
	@GetMapping("/code/{registrationId}")
    public String googleLogin(@RequestParam String code, @PathVariable String registrationId , HttpSession httpsession,Model model) {
        System.out.println("registrationId 확인 : "+registrationId);
        System.out.println("code 확인 : " + code);
        
        String googleResult = googleLoginService.socialLogin(code, registrationId, httpsession,model);
         
        if ("success".equals(googleResult)) {
            
        	
        	// 존재 시에는 홈페이지로 보내기~
            return "redirect:/";
        } else {
        	
            // 없으면 전화번호 받으러 보내버리기~~~~
            return "dw_view/05_SignUp";
        }
        
        
    }
	


}
