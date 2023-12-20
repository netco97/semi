package com.example.demo.dy.composer;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ComposerMapper {
	
	ComposerDTO findById(Long id);

}