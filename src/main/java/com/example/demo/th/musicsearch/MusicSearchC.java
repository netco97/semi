package com.example.demo.th.musicsearch;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MusicSearchC {
    private final MusicSearch_Model musicSearch_Model;

    public MusicSearchC(MusicSearch_Model musicSearch_Model) {
        this.musicSearch_Model = musicSearch_Model;
    }

    @GetMapping("/MusicSearchTag")
    public List<SongsDTO> MusicSearchTag(@RequestParam("genre") String genre,
                                         @RequestParam("mood") String mood,
                                         @RequestParam("instrument") String instrument) {
        
    	
    	List<SongsDTO> result = musicSearch_Model.searchMusicByTags(genre, mood, instrument);
    	
    	System.out.println(result);
    	return result;
    }
    
    @GetMapping("/MusicSearchText")
    public List<SongsDTO> MusicSearchText(@RequestParam("text") String text){
    	System.out.println("컨트롤러 text : " + text);
    	
    	List<SongsDTO> result = musicSearch_Model.searchMusicByText(text);
    	
    	
    	return result;
    }
    
    @GetMapping("/getMusicDetail")
    public List<SongsDTO> getMusicDetail(@RequestParam("song_id") int song_id){
    	System.out.println(song_id);
    	
    	List<SongsDTO> result = musicSearch_Model.getMusicDetail(song_id);
    	System.out.println("상세페이지 : " + result);
    	
    	return result;
    }
    
    
    
    
}
