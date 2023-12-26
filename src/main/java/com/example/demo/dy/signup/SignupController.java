package com.example.demo.dy.signup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;

@Controller
public class SignupController {
	
	    @Autowired
	    private SignupService signupService;

	    @GetMapping("/signup")
	    public String signup() {
	        return "dw_view/05_SignUp";
	    }

	    @PostMapping("/signup")
	    public String processSignup(@ModelAttribute SignupUserDTO user, RedirectAttributes redirectAttributes,HttpSession httpsession,Model model) {
	        signupService.savePhoneNumber(user,httpsession);
	        
	        model.addAttribute("iscomposer",0);
	        redirectAttributes.addFlashAttribute("signupSuccess", true);
	        
	        
	        return "dw_view/home";  
	    }
	

}
