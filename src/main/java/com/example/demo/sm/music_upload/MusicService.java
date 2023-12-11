package com.example.demo.sm.music_upload;

import java.util.List;
import java.util.StringJoiner;

import org.springframework.stereotype.Service;

@Service
public class MusicService {
	
	public static String listToString(List<String> list, String delimiter) {
        // StringJoiner를 사용하여 커스터마이징된 문자열 생성
        StringJoiner joiner = new StringJoiner(delimiter);
        for (String item : list) {
            joiner.add(item);
        }
        return joiner.toString();
    }
}
