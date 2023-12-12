package com.example.demo.sm.music_upload;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.sm.genre.GenreService;
import com.example.demo.sm.instrument.InstrumentService;
import com.example.demo.sm.mood.MoodMapper;
import com.example.demo.sm.mood.MoodService;

@Controller
public class MusicController {
	
	private final GenreService genreService;
	private final MoodService moodService;
	private final MusicService musicService;
	private final InstrumentService instrumentService;
	
	public MusicController(GenreService genreService, MoodService moodService, MusicService musicService, InstrumentService instrumentService) {
		this.genreService = genreService;
		this.moodService = moodService;
		this.musicService = musicService;
		this.instrumentService = instrumentService;
	}
	
	@GetMapping("/music_upload")
	public String music_reg() {
		
		return "sm/music_upload";
	}
	
	
	// form 값 받는컨트롤러
	@PostMapping("/upload")
	public String createMusicUpload(@ModelAttribute MusicUploadDTO musicUploadDTO) {
		
		//img , audio File 저장 & DB에 저장할 String List로 반환
		List<String> FileNames = musicService.saveFile(musicUploadDTO);
		System.out.println("이미지 파일 이름 테스트 " + FileNames.get(0) + "오디오 파일 이름 테스트 " + FileNames.get(1));
	    
	    List<String> musicMood = musicUploadDTO.getMusicMood();
	    List<String> musicInstrument = musicUploadDTO.getMusicInstrument();
	    
	    // mood 변수
	    List <String> mood_arr = new ArrayList<>();
	    String mood_result = "";
	    
	    // instrument 변수
	    List <String> instrument_arr = new ArrayList<>();
	    String instrument_result = "";
	    
	    // Test composer_name 변수
	    String composer_name = "sm";
	    
	    // 테스트 syso
	    System.out.println("음악 제목 : " + musicUploadDTO.getMusicTitle());
	    System.out.println("선택된 음악 장르: " + musicUploadDTO.getMusicGenre());
	    System.out.println("선택된 음악 분위기: " + musicMood);
	    System.out.println("선택된 악기 종류 : " + musicInstrument);
	    
	    int genrePK = genreService.getGenreId(musicUploadDTO.getMusicGenre());
	    System.out.println("장르 pk 확인 : " + genrePK);
	    for(String s : musicMood) {
	    	mood_arr.add(moodService.getMoodId(s));
	    }
	    for(String s : musicInstrument) {
	    	instrument_arr.add(instrumentService.getInstrumentId(s));
	    }
	    
	    mood_result = String.join("#", mood_arr);
	    instrument_result = String.join("#", instrument_arr);
	    
	    System.out.println("분위기 pk# 합친것 결과 : " + mood_result );
	    System.out.println("악기 pk# 합친것 결과 : " + instrument_result);
	    
	    
	    // songs DB save 세팅
	    SongsDTO songsDTO = SongsDTO.builder()
	    .song_name(musicUploadDTO.getMusicGenre())
	    .genre_id(genrePK)
	    .instrument_id(instrument_result)
	    .mood_id(mood_result)
	    .composer_name(composer_name)
	    .song_img(FileNames.get(0))
	    .song_audio(FileNames.get(1))
	    .build();
	    System.out.println(songsDTO);
	    // DB save
	    musicService.saveSongs(songsDTO);
	    
	    
	    return "sm/music_upload";
	}
	
}
