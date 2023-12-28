package com.example.demo.wk;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.ToString;

@Data
public class ComposerDTO {
	private String composer_id;
	private String composer_name;
	private String composer_genre;
	private String composer_text;
	private String composer_img;
	private MultipartFile composer_profilePicture;
	
	
	// 이미지가 없을 때 기본 이미지 파일 경로 반환
	public String getImgOrDefault() {
		if (composer_img != null && !composer_img.isEmpty()) {
			return composer_img;
		} else {
			return "default_profile.png";
		}
	}
}
