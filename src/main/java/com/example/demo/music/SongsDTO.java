package com.example.demo.music;

import lombok.Data;

@Data
public class SongsDTO {
	private int song_id;	
	private String song_name;	
	private int genre_id;	
	private int instrument_id;	
	private int composer_id;	
	private int mood_id;	
	private int is_copyrighted;	
	private String song_audio;
	private String song_img;
	 
}

