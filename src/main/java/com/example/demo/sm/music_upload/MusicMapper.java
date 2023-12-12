package com.example.demo.sm.music_upload;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MusicMapper {
	int regSongs(SongsDTO songsDTO);
}
