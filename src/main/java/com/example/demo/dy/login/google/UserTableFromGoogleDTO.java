package com.example.demo.dy.login.google;

import lombok.Data;

@Data
public class UserTableFromGoogleDTO {
    private String userFullPhoneNumber;
    private String userNickname;
    private String userEmail;
    
    public UserTableFromGoogleDTO(String userFullPhoneNumber, String userNickname, String userEmail ){
    	this.userFullPhoneNumber = userFullPhoneNumber;
    	this.userNickname = userNickname;
    	this.userEmail = userEmail;
    	
    }
}