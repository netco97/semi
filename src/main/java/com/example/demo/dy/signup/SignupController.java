package com.example.demo.dy.signup;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SignupController {
	
	@GetMapping("/signup")
	public String signup() {
		return "dw_view/05_SignUp"; 
	}

}
