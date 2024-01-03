package com.example.demo.wk.artist_rating;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/average-rating/{composer_id}")
    public double getAverageRating(@PathVariable String composer_id) {
        return artistRatingService.getAverageRating(composer_id);
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
    
    @GetMapping("/getRatingByUserAndComposer")
    public String getRatingByUserAndComposer(
        @RequestParam("userfullphonenumber") String userfullphonenumber,
        @RequestParam("composer_id") String composer_id
    ) {
    	ArtistRatingDTO artistRatingDTO = new ArtistRatingDTO();
    	artistRatingDTO.setUserfullphonenumber(userfullphonenumber);
    	artistRatingDTO.setComposer_id(composer_id);
    	System.out.println("컨트롤러 userfullphonenumber: " + userfullphonenumber);
    	System.out.println("컨트롤러 composer_id: " + composer_id);
        return artistRatingService.getRatingByUserAndComposer(artistRatingDTO);
    }

    
    
    

}
