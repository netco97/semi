package com.example.demo.dy.login.google;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserTableFromGoogleMapper {
	 UserTableFromGoogleDTO selectUserTableInfo(String userEmail);
}
