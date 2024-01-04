package com.example.demo.wk.artist_rating;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ArtistRatingService {

	private final ArtistRatingMapper artistRatingMapper;

	public void insertRating(ArtistRatingDTO ratingDTO) {
		artistRatingMapper.insertRating(ratingDTO);
	}

	public double getAverageRating(String composer_id) {
		return artistRatingMapper.getAverageRating(composer_id);
	}

	public void updateRating(ArtistRatingDTO ratingDTO) {
		artistRatingMapper.updateRating(ratingDTO);
	}

	public void deleteRating(ArtistRatingDTO ratingDTO) {
		artistRatingMapper.deleteRating(ratingDTO);
	}
	
	public String getRatingByUserAndComposer(ArtistRatingDTO ratingDTO) {
		return artistRatingMapper.getRatingByUserAndComposer(ratingDTO);
	}
	
}
