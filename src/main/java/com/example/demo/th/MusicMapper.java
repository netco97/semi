package com.example.demo.th;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MusicMapper {
	
	
	List<SongsDTO> searchTag(String tag);

	List<SongsDTO> searchGenreId(String s);

	List<SongsDTO> searchMoodId(String s);

	List<SongsDTO> searchInstrumentId(String s);






	
	
	

}
