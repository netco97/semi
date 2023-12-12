package com.example.demo.sm.mood;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class MoodService {
	private final MoodMapper moodMapper;
	
	public MoodService(MoodMapper moodMapper) {
		this.moodMapper = moodMapper;
	}
	public String getMoodId(String mood){	
		return moodMapper.getMood(mood);
	}
}
