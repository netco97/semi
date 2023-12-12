package com.example.demo.sm.mood;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MoodMapper {
	String getMood(String mood);
}
