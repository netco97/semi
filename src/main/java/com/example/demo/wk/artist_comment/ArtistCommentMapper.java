package com.example.demo.wk.artist_comment;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ArtistCommentMapper {
	
	List<ArtistCommentDTO> getAllCommentsByComposerId(String composer_id);
	
    List<ArtistCommentDTO> getCommentsByComposerIdWithPaging(
            @Param("composer_id") String composer_id,
            @Param("offset") int offset,
            @Param("limit") int limit);

    int countCommentsByComposerId(@Param("composer_id") String composer_id);
	
	void insertComment(ArtistCommentDTO comment);
	
	void deleteComment(Long comment_id);
	

}
