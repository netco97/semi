package com.example.demo.th;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

        List<SongsDTO> getSongs = new ArrayList<>();
        if (!(genre.equals(""))) {

            // #을 기준으로 문자열을 분리하고 배열에 저장
            String[] genreArray = genre.split("#");
            for (String g : genreArray) {
                System.out.println("장르 하나씩 : " + g);
                getSongs.addAll(musicSearch_Model.getGenreId(g));
            }

        }

        if (!(mood.equals(""))) {

            String[] moodArray = mood.split("#");
            for (String m : moodArray) {
                System.out.println("무드 하나씩 : " + m);
                getSongs.addAll(musicSearch_Model.getMoodId(m));
            }
        }

        if (!(instrument.equals(""))) {

            String[] instrumentArray = instrument.split("#");
            for (String i : instrumentArray) {
                System.out.println("분위기 하나씩 : " + i);
                getSongs.addAll(musicSearch_Model.getInstrumentId(i));
            }
        }
        return getSongs; 
    }
}
