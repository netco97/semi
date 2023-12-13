package com.example.wk;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
	
	@Autowired
	private CommentMapper commentMapper;
	
	public List<CommentDTO> getCommentsByComposerId(Long composerId) {
		return commentMapper.getCommentsByComposerId(composerId);
	}
	
	public void insertComment(CommentDTO comment) {
		commentMapper.insertComment(comment);
	}

}
