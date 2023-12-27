package com.example.demo.dy.signup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;

@Controller
public class SignupController {
	
	    @Autowired
	    private SignupService signupService;
	    
	    @GetMapping("/checkDuplicatePhoneNumber")
	    @ResponseBody
	    public int check(@RequestParam("phoneNumber") String phoneNumber) {
	        System.out.println("test 중입니다.");
	        
	        // 중복이면 1, 중복이 아니면 0 반환
	        return signupService.checkPhoneNumberDuplicate(phoneNumber) != null ? 1 : 0;
	    }
	    

	    @PostMapping("/signup")
	    public String processSignup(@ModelAttribute SignupUserDTO user, RedirectAttributes redirectAttributes,HttpSession httpsession,Model model) {
	        signupService.savePhoneNumber(user,httpsession);
	        
	        httpsession.setAttribute("iscomposer", 0);
	        
	        redirectAttributes.addFlashAttribute("signupSuccess", true);
	        
	        
	        return "redirect:/";
	    }
	

}
