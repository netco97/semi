package com.example.wk;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ComposerMapper {
	
	ComposerDTO findById(Long id);

}
