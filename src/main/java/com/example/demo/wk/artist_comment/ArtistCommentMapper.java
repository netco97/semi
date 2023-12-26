package com.example.demo.wk.artist_comment;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ArtistCommentMapper {
	
	List<ArtistCommentDTO> getAllCommentsByComposerId(Long composerId);
	
    List<ArtistCommentDTO> getCommentsByComposerIdWithPaging(
            @Param("composerId") Long composerId,
            @Param("offset") int offset,
            @Param("limit") int limit);

    int countCommentsByComposerId(@Param("composerId") Long composerId);
	
	void insertComment(ArtistCommentDTO comment);
	
	void deleteComment(Long commentId);
	

}
