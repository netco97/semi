package com.example.demo.dy.login.kakao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;



@Mapper	

public interface KakaoMapper {
	int RegUser(KakaoUserDTO kakaoUserDTO);

	List<KakaoUserDTO> SelUser(String string);


}
