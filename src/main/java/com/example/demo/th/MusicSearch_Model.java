package com.example.demo.th;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class MusicSearch_Model {
    private final MusicMapper musicMapper;

    public MusicSearch_Model(MusicMapper musicMapper) {
        this.musicMapper = musicMapper;
    }

    public List<SongsDTO> getGenreId(String g) {
        return musicMapper.searchGenreId(g);
    }

    public List<SongsDTO> getMoodId(String m) {
        return musicMapper.searchMoodId(m);
    }

    public List<SongsDTO> getInstrumentId(String i) {
        return musicMapper.searchInstrumentId(i);
    }
}


