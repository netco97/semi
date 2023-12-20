package com.example.demo.dy.login.google;


import org.springframework.stereotype.Controller;
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
    public String googleLogin(@RequestParam String code, @PathVariable String registrationId , HttpSession httpsession) {
        System.out.println("registrationId 확인 : "+registrationId);
        System.out.println("code 확인 : " + code);
        
        googleLoginService.socialLogin(code, registrationId, httpsession);
        
        return "dw_view/05_SignUp";
    }
	


}
