package com.example.demo.th.music_comment;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

@Mapper
public interface MusicCommentMapper {

	List<MusicCommentDTO> getComments(int song_id);

//	int regComment(int song_id,String fullphonenumber,String comment_text);
	int regComment(@Param("song_id") int song_id,@Param("fullphonenumber") String fullphonenumber,
			@Param("comment_text") String comment_text);
	


}
