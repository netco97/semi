// SignupMapper.java
package com.example.demo.dy.signup;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SignupMapper {
    int saveUser(SignupUserDTO user);

	public Integer findByPhoneNumber(@Param("userFullPhoneNumber") String userFullPhoneNumber);
}