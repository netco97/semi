package com.example.demo.wk.artist_rating;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

@Mapper
public interface ArtistRatingMapper {
	void insertRating(ArtistRatingDTO ratingDTO);
    double getAverageRating(String composer_id);
    void updateRating(ArtistRatingDTO ratingDTO);
    void deleteRating(ArtistRatingDTO ratingDTO);
    String getRatingByUserAndComposer(ArtistRatingDTO ratingDTO);
}
