package com.example.demo.th.music_upload;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.th.musicsearch.SongsDTO;

import jakarta.servlet.http.HttpSession;

@Controller
public class MusicUploadController {
	private final MusicUploadService musicUploadService;
	
	public MusicUploadController(MusicUploadService musicUploadService) {
		this.musicUploadService = musicUploadService;
	}
	
	@GetMapping("/musicUpload")
	public String music_reg(Model model) {
		
		model.addAttribute("content", "th/musicUpload");
		
		return "wk/index";
	}
	
	// form 값 받는컨트롤러
	@PostMapping("/upload")
	public String createMusicUpload(@ModelAttribute MusicUploadDTO musicUploadDTO,HttpSession session) {
		
		//img , audio File 저장 & DB에 저장할 String List로 반환
		List<String> FileNames = musicUploadService.saveFile(musicUploadDTO);
		System.out.println("이미지 파일 이름 테스트 " + FileNames.get(0) + "오디오 파일 이름 테스트 " + FileNames.get(1));
	    
	    List<String> musicMood = musicUploadDTO.getMusicMood();
	    List<String> musicInstrument = musicUploadDTO.getMusicInstrument();
	    
	    // mood 변수
	    String mood_result = "";
	    mood_result = String.join("#", musicMood);
	    
	    // instrument 변수
	    String instrument_result = "";
	    instrument_result = String.join("#", musicInstrument);
	    
	    // composer_name 변수
	    String composer_name = session.getAttribute("userNickname").toString();

	    int song_like = 0;
	    
	    // 테스트 syso
	    System.out.println("음악 제목 : " + musicUploadDTO.getMusicTitle());
	    System.out.println("선택된 음악 장르: " + musicUploadDTO.getMusicGenre());
	    System.out.println("선택된 음악 분위기: " + musicMood);
	    System.out.println("선택된 악기 종류 : " + musicInstrument);
	    
	    System.out.println("분위기 pk# 합친것 결과 : " + mood_result );
	    System.out.println("악기 pk# 합친것 결과 : " + instrument_result);
	    	
	    
	    // songs DB save 세팅
	    SongsDTO songsDTO = SongsDTO.builder()
	    .song_name(musicUploadDTO.getMusicTitle())
	    .genre_id(musicUploadDTO.getMusicGenre())
	    .instrument_id(instrument_result)
	    .mood_id(mood_result)
	    .composer_name(composer_name)
	    .song_img(FileNames.get(0))
	    .song_audio(FileNames.get(1))
	    .song_like(song_like)
	    .build();
	    
	    System.out.println(songsDTO);
	    // DB save
	    musicUploadService.saveSongs(songsDTO);
	    
	    int result_song_id = musicUploadService.getSongId(songsDTO.getSong_name());
	    
	    return "redirect:/musicDetail?song_id="+result_song_id;
	}
	
}