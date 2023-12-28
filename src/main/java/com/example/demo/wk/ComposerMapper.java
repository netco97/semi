package com.example.demo.wk;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ComposerMapper {
	
	ComposerDTO findById(@Param("composer_id") String composer_id);
	int regComposer(ComposerDTO composer);
	int updateUserIsComposer(@Param("userFullPhoneNumber") String userFullPhoneNumber);
}
