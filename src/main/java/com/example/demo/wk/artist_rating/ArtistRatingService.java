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
	
    public boolean hasUserRatedArtist(String userfullphonenumber, String composer_id) {
        // 사용자가 이미 아티스트에 대해 평가했는지 확인하는 로직을 구현
        // 기존의 메서드를 사용하거나 매퍼에 새로운 메서드를 추가할 수 있습니다.
        // 예를 들어, artistRatingMapper.hasUserRatedArtist(userfullphonenumber, composer_id)
        // 사용자가 이미 아티스트에 대해 평가한 경우 true를 반환하고, 그렇지 않으면 false를 반환
        return artistRatingMapper.hasUserRatedArtist(userfullphonenumber, composer_id) > 0;
    }
	
}
