package com.example.demo.th.music_like;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

@Mapper
public interface MusicLikeMapper {

	MusicLikeDTO checkLike(@Param("song_id") int song_id, @Param("userFullPhoneNumber") String userFullPhoneNumber);

	int insertLike(@Param("song_id") int song_id, @Param("userFullPhoneNumber") String userFullPhoneNumber);

	int updateLike(@Param("song_like_id") int song_like_id,@Param("isLike") int isLike);

		
		
	

	
	


}
