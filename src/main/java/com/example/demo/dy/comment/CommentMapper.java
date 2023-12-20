package com.example.demo.dy.comment;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CommentMapper {
	
	List<CommentDTO> getAllCommentsByComposerId(Long composerId);
	
    List<CommentDTO> getCommentsByComposerIdWithPaging(
            @Param("composerId") Long composerId,
            @Param("offset") int offset,
            @Param("limit") int limit);

    int countCommentsByComposerId(@Param("composerId") Long composerId);
	
	void insertComment(CommentDTO comment);
	void deleteComment(Long commentId);
	

}