package com.example.demo.wk.artist_rating;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ArtistRatingDTO {
	private Long ratingId;
	private String userFullNumber;
    private Long composerId;
    private double rating;

}
