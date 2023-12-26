package com.example.demo.wk.artist_rating;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArtistRatingMapper {
	void insertRating(ArtistRatingDTO ratingDTO);
    double getAverageRating(Long composerId);
    void updateRating(ArtistRatingDTO ratingDTO);
    void deleteRating(ArtistRatingDTO ratingDTO);
}
