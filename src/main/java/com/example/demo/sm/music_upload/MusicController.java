package com.example.demo.sm.music_upload;

import java.util.List;
import java.util.StringJoiner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class MusicController {
	
	@GetMapping("/music_upload")
	public String music_reg() {
		
		return "sm/music_upload";
	}
	
	
	// form 값 받는컨트롤러
	@PostMapping("/upload")
	public String createMusicUpload(@ModelAttribute MusicUploadDTO musicUploadDTO) {
	    MultipartFile file = musicUploadDTO.getMusicFile();
	    MultipartFile albumArt = musicUploadDTO.getAlbumArt();
	    List<String> musicMood = musicUploadDTO.getMusicMood();
	    List<String> musicInstrument = musicUploadDTO.getMusicInstrument();
	    int isComposer = 0;
	    
	    // 테스트 syso
	    System.out.println("음악 제목 : " + musicUploadDTO.getMusicTitle());
	    System.out.println("음악 파일: " + file);
	    System.out.println("파일 오리지널 네임: " + file.getOriginalFilename());
	    System.out.println("앨범 아트 원본 파일명: " + albumArt.getOriginalFilename());
	    System.out.println("선택된 음악 장르: " + musicUploadDTO.getMusicGenre());
	    System.out.println("선택된 음악 분위기: " + musicMood);
	    System.out.println("선택된 악기 종류 : " + musicInstrument);
	    
	    String result_musicInstrument = MusicService.listToString(musicInstrument, "#");
	    String result_musicMood = MusicService.listToString(musicMood, "#");
	    
	    System.out.println("선택된 음악 분위기 (#커스터마이징) :" + result_musicMood);
	    System.out.println("선택된 악기 (#커스터마이징) :" + result_musicInstrument);
	    
	    
	    
	    return "sm/music_upload";
	}
	
}
