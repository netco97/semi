// SignupService.java
package com.example.demo.dy.signup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;

@Service
public class SignupService {

    @Autowired
    private SignupMapper signupMapper;

    public void saveUser(SignupUserDTO user, HttpSession httpsession) {
        if(signupMapper.saveUser(user)==1)
        {
        	httpsession.setAttribute("userFullPhoneNumber", user.getUserFullPhoneNumber());
        	System.out.println("UserTable 등록 성공 !");
        }
    }

    public void savePhoneNumber(SignupUserDTO user, HttpSession httpSession) {
    	SignupUserDTO result = new SignupUserDTO(user.getFullPhoneNumber(),user.getUserNickname(),user.getUserEmail(),"0","0");
    	
    	saveUser(result, httpSession);
    	
    	
    	
    	
    }
}