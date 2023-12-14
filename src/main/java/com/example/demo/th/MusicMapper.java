package com.example.demo.th;

import java.util.Collection;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import ch.qos.logback.core.recovery.ResilientSyslogOutputStream;

@Mapper
public interface MusicMapper {

	List<SongsDTO> searchTag(@Param("genreArray") String[] genreArray, @Param("moodArray") String[] moodArray,
			@Param("instrumentArray") String[] instrumentArray

	);

	List<SongsDTO> searchText(String text);

	List<SongsDTO> getMusicDetail(int song_id);

//	List<SongsDTO> searchGenreId(String[] genreArray);
//
//	List<SongsDTO> searchMoodId(@Param(value = "moodArray") String[] moodArray, );
//
//	List<SongsDTO> searchInstrumentId(String[] instrumentArray);

}
