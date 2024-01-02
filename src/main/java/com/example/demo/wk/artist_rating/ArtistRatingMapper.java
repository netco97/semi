package com.example.demo.wk.artist_rating;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ArtistRatingMapper {
	void insertRating(ArtistRatingDTO ratingDTO);
    double getAverageRating(String composer_id);
    void updateRating(ArtistRatingDTO ratingDTO);
    void deleteRating(ArtistRatingDTO ratingDTO);
    int getRatingByUserAndComposer(
            @Param("userfullphonenumber") String userfullphonenumber,
            @Param("composer_id") String composer_id
        );
}
