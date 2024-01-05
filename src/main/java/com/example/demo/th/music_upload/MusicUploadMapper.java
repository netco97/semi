package com.example.demo.th.music_upload;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.th.musicsearch.SongsDTO;

@Mapper
public interface MusicUploadMapper {
	int regSongs(SongsDTO songsDTO);

	int getSongId(@Param("song_name") String song_name);
}