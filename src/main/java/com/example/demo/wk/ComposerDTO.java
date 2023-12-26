package com.example.demo.wk;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ComposerDTO {
	private Long id;
	private String name;
	private String genre;
	private String text;
	private String img;
	private MultipartFile profilePicture;
	
	// 이미지가 없을 때 기본 이미지 파일 경로 반환
	public String getImgOrDefault() {
		if (img != null && !img.isEmpty()) {
			return img;
		} else {
			return "default_profile.png";
		}
	}
}
