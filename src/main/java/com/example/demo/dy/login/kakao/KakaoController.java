package com.example.demo.dy.login.kakao;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;



@Controller
@RequestMapping(value = "/login", produces = "application/json")
public class KakaoController {
	private final KakaoLoginService kakaoLoginService;
	
	public KakaoController(KakaoLoginService kakaoLoginService) {
		this.kakaoLoginService = kakaoLoginService;
	}
	@GetMapping("/kakao")
	   public String kakaoLogin(@RequestParam String code, HttpSession httpsession) {
	        System.out.println("code 확인 : " + code);
		System.out.println("카카오컨트롤러");
	        
	      String kakaoResult =  kakaoLoginService.socialLogin(code,"kakao", httpsession);
	        
	      if ("success".equals(kakaoResult)) {
	            
	        	// 존재 시에는 홈페이지로 보내기~
	            return "dw_view/home";
	        } else {
	        	
	            // 없으면 전화번호 받으러 보내버리기~~~~
	            return "dw_view/05_SignUp";
	        }
	}

}
