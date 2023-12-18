package com.example.demo.dy.signup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SignupController {
	
	    @Autowired
	    private SignupService signupService;

	    @GetMapping("/signup")
	    public String signup() {
	        return "dw_view/05_SignUp";
	    }

	    @PostMapping("/signup")
	    @ResponseBody
	    public String processSiSgnup(@ModelAttribute SignupUserDTO user) {
	    	signupService.savePhoneNumber(user);
	        return "dw_view/06";  // 회원가입 성공 후 이동할 페이지
	    }
	

}
