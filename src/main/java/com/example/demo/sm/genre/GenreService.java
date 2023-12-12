package com.example.demo.sm.genre;

import org.springframework.stereotype.Service;

@Service
public class GenreService {
	private final GenreMapper genreMapper;
	
	public GenreService(GenreMapper genreMapper) {
		this.genreMapper = genreMapper;
	}
	public int getGenreId(String genre) {
		return genreMapper.getGenre(genre);
	}
}
