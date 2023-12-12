package com.example.demo.th;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class MusicSearch_Model {

	private final MusicMapper musicMapper;

	public MusicSearch_Model(MusicMapper musicMapper) {
		this.musicMapper = musicMapper;
	}

	public List<SongsDTO> MusicSearchTag(String tags) {
	
		return musicMapper.searchTag(tags);
	
	}


}
