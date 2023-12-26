package com.example.demo.dy.login.google;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;



/*
@Getter
@Setter
@ToString
*/
@Data
public class GoogleUserDTO {
	private String user_id;
	private String user_email;
	private String user_nickname;
	
	
	public GoogleUserDTO(String user_id, String user_email, String user_nickname) {
		this.user_id = user_id;
		this.user_email = user_email;
		this.user_nickname = user_nickname;
	}
}
