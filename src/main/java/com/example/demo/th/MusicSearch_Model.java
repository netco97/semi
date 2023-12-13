package com.example.demo.th;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class MusicSearch_Model {
	private final MusicMapper musicMapper;

	public MusicSearch_Model(MusicMapper musicMapper) {
		this.musicMapper = musicMapper;
	}

	public List<SongsDTO> searchMusicByTags(String genre, String mood, String instrument) {
		List<SongsDTO> result = new ArrayList<>();
		String[] genreArray = null;
		String[] moodArray = null;
		String[] instrumentArray = null;

		if (!(genre.equals(""))) {
			genreArray = genre.split("#");
		}else {
			genreArray = new String[1];
			genreArray[0] = "all";
		}
		

		if (!(mood.equals(""))) {
			moodArray = mood.split("#");
		}else {
			moodArray = new String[1];
			moodArray[0] = "all";
		}

		if (!(instrument.equals(""))) {
			 instrumentArray = instrument.split("#");
		}else {
			instrumentArray = new String[1];
			instrumentArray[0] = "all";
		}
		result = musicMapper.searchTag(genreArray,moodArray,instrumentArray);
		System.out.println(result);
		
		return result;
	}
	
}
