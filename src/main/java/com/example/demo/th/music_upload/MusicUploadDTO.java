package com.example.demo.th.music_upload;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class MusicUploadDTO {
	private String musicTitle;
	private MultipartFile musicFile;
    private MultipartFile albumArt;
    private String musicGenre;
    private List<String> musicMood;
    private List<String> musicInstrument;
    
    MusicUploadDTO(){
    	
    }
}