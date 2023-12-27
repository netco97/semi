package com.example.demo.dy.login.kakao;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dy.login.google.GoogleLoginService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;



@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/login", produces = "application/json")
public class KakaoController {
	private final KakaoLoginService kakaoLoginService;
	
	
	@GetMapping("/kakao")
	   public String kakaoLogin(@RequestParam String code, HttpSession httpsession,Model model) {
	        System.out.println("code 확인 : " + code);
		System.out.println("카카오컨트롤러");
	        
	      String kakaoResult =  kakaoLoginService.socialLogin(code,"kakao", httpsession,model);
	        
	      if ("success".equals(kakaoResult)) {
	            
	        	// 존재 시에는 홈페이지로 보내기~
	    	  	return "redirect:/";
	        } else {
	        	
	            // 없으면 전화번호 받으러 보내버리기~~~~
	            return "dw_view/05_SignUp";
	        }
	}

}
