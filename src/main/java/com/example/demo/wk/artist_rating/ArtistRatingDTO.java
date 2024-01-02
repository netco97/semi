package com.example.demo.wk.artist_rating;

import lombok.Data;
import lombok.ToString;

@Data
public class ArtistRatingDTO {
	private Long rating_id;
	private String userfullphonenumber;
    private String composer_id;
    private double rating;

}
