// SignupMapper.java
package com.example.demo.dy.signup;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SignupMapper {
    int saveUser(SignupUserDTO user);
}