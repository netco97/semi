package com.example.wk;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapper {
	
	List<CommentDTO> getAllCommentsByComposerId(Long composerId);
	void insertComment(CommentDTO comment);
	void deleteComment(Long commentId);
	

}
