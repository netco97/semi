package com.example.demo.th;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SongsDTO {
	private int song_id;	
	private String song_name;	
	private int genre_id;	
	private int instrument_id;	
	private int composer_id;	
	private int mood_id;
	private String song_audio;
	private String song_img;
	 
}

