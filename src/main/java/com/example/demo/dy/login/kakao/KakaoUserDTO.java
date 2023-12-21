package com.example.demo.dy.login.kakao;

import lombok.Data;

@Data
public class KakaoUserDTO {
	private String user_id;
	private String user_email;
	private String user_nickname;
	
	public KakaoUserDTO(String user_id, String user_email, String user_nickname) {
		this.user_id = user_id;
		this.user_email = user_email;
		this.user_nickname = user_nickname;
	}

}
