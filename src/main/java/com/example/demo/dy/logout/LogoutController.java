package com.example.demo.dy.logout;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class LogoutController {
	 @GetMapping("/logout")
	    public String logout(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
	        
		 //세션 꺼져~~~
	        session.invalidate();

	     // 쿠키 꺼져~~~
	        Cookie[] cookies = request.getCookies();
	        if (cookies != null) {
	            for (Cookie cookie : cookies) {
	                cookie.setMaxAge(0);
	                response.addCookie(cookie);
	            }
	        }

	       
	        return "redirect:/";
	    }
	}

	   
	
		
		
	


