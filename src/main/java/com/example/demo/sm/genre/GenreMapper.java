package com.example.demo.sm.genre;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GenreMapper {
	int getGenre(String genre);
}
