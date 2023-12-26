package com.example.demo.dy.login.kakao;

import lombok.Data;

@Data
public class UserTableFromKakaoDTO {
	private String userFullPhoneNumber;
	private String userNickname;
	private String userEmail;

	public UserTableFromKakaoDTO(String userFullPhoneNumber, String userNickname, String userEmail) {
		this.userFullPhoneNumber = userFullPhoneNumber;
		this.userNickname = userNickname;
		this.userEmail = userEmail;

	}

}
