package com.example.demo.sm.music_upload;

import lombok.Builder;
import lombok.Data;

@Data
public class SongsDTO {
    private int song_id;
    private String song_name;
    private int genre_id;
    private String instrument_id;
    private String mood_id;
    private String composer_name;
    private String song_img;
    private String song_audio;
    
    @Builder
    public SongsDTO(String song_name, int genre_id, String instrument_id, String mood_id, String composer_name, String song_img, String song_audio) {
    	this.song_name = song_name;
    	this.genre_id = genre_id;
    	this.instrument_id = instrument_id;
    	this.mood_id = mood_id;
    	this.composer_name = composer_name;
    	this.song_img = song_img;
    	this.song_audio = song_audio;
    	
    }
    
    public SongsDTO() {
    	
    }
}
