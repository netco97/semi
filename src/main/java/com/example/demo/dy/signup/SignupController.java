package com.example.demo.dy.signup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SignupController {
	
	    @Autowired
	    private SignupService signupService;

	    @GetMapping("/signup")
	    public String signup() {
	        return "dw_view/05_SignUp";
	    }

	    @PostMapping("/signup")
	    public String processSignup(@ModelAttribute SignupUserDTO user, RedirectAttributes redirectAttributes) {
	        signupService.savePhoneNumber(user);

	        redirectAttributes.addFlashAttribute("signupSuccess", true);
	        

	        return "dw_view/home";  // 회원가입 성공 후 이동할 페이지
	    }
	

}
