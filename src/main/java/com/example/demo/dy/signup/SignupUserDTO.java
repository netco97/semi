package com.example.demo.dy.signup;

import lombok.Data;

@Data
public class SignupUserDTO {
    private String userId;
    private String userNickname;
    private String userEmail;
    private String phoneNumberPart1;
    private String phoneNumberPart2;
    private String phoneNumberPart3;
    private String userFullPhoneNumber;
    private String userSongs;
    private double userStars;
    private String userPr;
    private int iscomposer;

    // 전화번호를 합쳐서 반환하는 메서드 추가
    public String getFullPhoneNumber() {
        return phoneNumberPart1 + phoneNumberPart2 + phoneNumberPart3;
    }

    // 기본 생성자 추가
    public SignupUserDTO() {
        this.userStars = 0;
    }

    // 생성자 추가
    public SignupUserDTO(String userFullPhoneNumber, String userNickname, String userEmail, String userSongs, String userPr,int iscomposer) {
        this.userFullPhoneNumber = userFullPhoneNumber;
        this.userNickname = userNickname;
        this.userEmail = userEmail;
        this.userSongs = userSongs;
        this.userPr = userPr;
        this.iscomposer = iscomposer;
        
    }
}