package com.example.demo.th.musicsearch;



import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MusicService {

    private final MusicMapper musicMapper;

    public TagsDTO getTags() {
        
        TagsDTO tags = new TagsDTO(); 


        tags.setGenre(musicMapper.getGenre());
        tags.setMood(musicMapper.getMood());
        tags.setInstrument(musicMapper.getInstrument());
        


        System.out.println(tags);

        return tags;
    }

   
}