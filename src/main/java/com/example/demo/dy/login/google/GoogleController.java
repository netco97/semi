package com.example.demo.dy.login.google;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/login/oauth2", produces = "application/json")
public class GoogleController {
	private final GoogleLoginService googleLoginService;
	
	public GoogleController(GoogleLoginService googleLoginService) {
		this.googleLoginService = googleLoginService;
	}
	
	@GetMapping("/code/{registrationId}")
    public String googleLogin(@RequestParam String code, @PathVariable String registrationId , HttpSession httpsession) {
        System.out.println("registrationId 확인 : "+registrationId);
        System.out.println("code 확인 : " + code);
        
        googleLoginService.socialLogin(code, registrationId, httpsession);
        
        return "dw_view/05_SignUp";
    }
	


}
