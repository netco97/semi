package com.example.demo.dy.login.google;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GoogleMapper {
	int RegUser(GoogleUserDTO googleUserDTO);

	List<GoogleUserDTO> SelUser(String string);
}
