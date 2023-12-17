package com.example.wk;

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
	
	// 이미지가 없을 때 기본 이미지 파일 경로 반환
	public String getImgOrDefault() {
		if (img != null && !img.isEmpty()) {
			return "/images/" + img;
		} else {
			return "/images/default_profile.png";
		}
	}
}
