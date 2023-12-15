package com.example.wk;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
	
	@Autowired
	private CommentMapper commentMapper;
	
	public List<CommentDTO> getCommentsByComposerId(Long composerId) {
		System.out.println(composerId);
		return commentMapper.getAllCommentsByComposerId(composerId);
	}
	
	public void insertComment(CommentDTO comment) {
		commentMapper.insertComment(comment);
		System.out.println(comment);
	}
	
	public void deleteCommentById(Long commentId) {
		commentMapper.deleteCommentById(commentId);
	}

}
