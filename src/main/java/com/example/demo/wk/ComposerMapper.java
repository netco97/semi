package com.example.demo.wk;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ComposerMapper {
	
	ComposerDTO findById(Long id);
	void insertComposer(ComposerDTO composer);
}
