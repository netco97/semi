package com.example.demo.th.musicsearch;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MusicSearchC {
    private final MusicSearch_Model musicSearch_Model;

    public MusicSearchC(MusicSearch_Model musicSearch_Model) {
        this.musicSearch_Model = musicSearch_Model;
    }

    @PostMapping("/MusicSearchTag")
    public List<SongsDTO> MusicSearchTag(@RequestParam("genre") String genre,
                                         @RequestParam("mood") String mood,
                                         @RequestParam("instrument") String instrument) {
        
    	
    	List<SongsDTO> result = musicSearch_Model.searchMusicByTags(genre, mood, instrument);
    	
    	for(SongsDTO s : result) {
    		s.setUserFullPhoneNumber(musicSearch_Model.getComposerFullPhoneNumber(s.getComposer_name()));
    	}
    	
    	return result;
    }
//    @GetMapping("/MusicSearchTag")
//    public List<SongsDTO> MusicSearchTag(@RequestParam("genre") String genre,
//    		@RequestParam("mood") String mood,
//    		@RequestParam("instrument") String instrument) {
//    	
//    	
//    	List<SongsDTO> result = musicSearch_Model.searchMusicByTags(genre, mood, instrument);
//    	
//    	System.out.println(result);
//    	return result;
//    }
    
    @GetMapping("/MusicSearchText")
    public List<SongsDTO> MusicSearchText(@RequestParam("text") String text){
    	System.out.println("컨트롤러 text : " + text);
    	
    	List<SongsDTO> result = musicSearch_Model.searchMusicByText(text);
    	
    	for(SongsDTO s : result) {
    		s.setUserFullPhoneNumber(musicSearch_Model.getComposerFullPhoneNumber(s.getComposer_name()));
    	}
    	
    	return result;
    }
    
    @GetMapping("/getMusicDetail")
    public List<SongsDTO> getMusicDetail(@RequestParam("song_id") int song_id){
    	System.out.println(song_id);
    	
    	List<SongsDTO> result = musicSearch_Model.getMusicDetail(song_id);
    	System.out.println("상세페이지 : " + result);
    	
    	return result;
    }
    

    @GetMapping("/getComposerMusic")
    public List<SongsDTO> getComposerMusic(@RequestParam("composer_id") String composer_id){

    		System.out.println("작곡가 이름 :::::"+ composer_id);
    	
        List<SongsDTO> result = musicSearch_Model.getComposerMusic(composer_id);
        

        return result;

    }
    
    @GetMapping("/GetIpodMusic")
    public List<SongsDTO> GetIpodMusic(@RequestParam("keyWord") String keyWord){
		
    	List<SongsDTO> result = new ArrayList<>();
    	
    	if(keyWord.equals("hot")) {
    		result = musicSearch_Model.getHotMusic();
    		
    		
    	}else {
    		result = musicSearch_Model.getNewMusic();
    		
    	}
    	
    	for(SongsDTO s : result) {
    		s.setUserFullPhoneNumber(musicSearch_Model.getComposerFullPhoneNumber(s.getComposer_name()));
    	}
    	
    	return result;
    	
    }
    
//    @GetMapping("/MainTextSearch")
//    public List<SongsDTO> MainTextSearch(@RequestParam("text")String text){
//    	
//    	
//    	
//    	
//    	return result;
//    }
    
    
    
    

    
    
}
