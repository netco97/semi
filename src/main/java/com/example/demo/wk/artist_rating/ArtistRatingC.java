package com.example.demo.wk.artist_rating;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/ratings")
@RequiredArgsConstructor
public class ArtistRatingC {
	private final ArtistRatingService artistRatingService;
	
    @PostMapping("/rate")
    public void addRating(@RequestBody ArtistRatingDTO ratingDTO) {
        artistRatingService.insertRating(ratingDTO);
        System.out.println(ratingDTO);
    }

    @GetMapping("/average-rating/{composerId}")
    public double getAverageRating(@PathVariable Long composerId) {
        return artistRatingService.getAverageRating(composerId);
    }

    @PutMapping("/update-rating")
    public void updateRating(@RequestBody ArtistRatingDTO ratingDTO) {
        artistRatingService.updateRating(ratingDTO);
        System.out.println(ratingDTO);
    }

    @DeleteMapping("/delete-rating")
    public void deleteRating(@RequestBody ArtistRatingDTO ratingDTO) {
        artistRatingService.deleteRating(ratingDTO);
        System.out.println(ratingDTO);
    }

}
