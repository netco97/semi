package com.example.demo.dy.login.kakao;

import lombok.Data;

@Data
public class UserTableFromKakaoDTO {
	private String userFullPhoneNumber;
	private String userNickname;
	private String userEmail;
	private int iscomposer;

	public UserTableFromKakaoDTO(String userFullPhoneNumber, String userNickname, String userEmail,int iscomposer) {
		this.userFullPhoneNumber = userFullPhoneNumber;
		this.userNickname = userNickname;
		this.userEmail = userEmail;
		this.iscomposer = iscomposer;

	}

}
