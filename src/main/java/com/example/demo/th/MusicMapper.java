package com.example.demo.th;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MusicMapper {

	String[] getGenre();
	
	String[] getMood();

	String[] getInstrument();
	
	

}
