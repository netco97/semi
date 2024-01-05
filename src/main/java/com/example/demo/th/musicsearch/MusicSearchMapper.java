package com.example.demo.th.musicsearch;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface MusicSearchMapper {

	List<SongsDTO> searchTag(@Param("genreArray") String[] genreArray, @Param("moodArray") String[] moodArray,
			@Param("instrumentArray") String[] instrumentArray);

	List<SongsDTO> searchText(String text);

	List<SongsDTO> getMusicDetail(int song_id);

	List<SongsDTO> getComposerMusic(String composer_id);

	String getComposerFullPhoneNumber(@Param("composer_name") String composer_name);



}
