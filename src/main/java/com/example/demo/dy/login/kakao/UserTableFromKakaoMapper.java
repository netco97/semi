package com.example.demo.dy.login.kakao;

import org.apache.ibatis.annotations.Mapper;



@Mapper
public interface UserTableFromKakaoMapper {
	UserTableFromKakaoDTO selectUserTableInfo(String userEmail);
	
	

}
