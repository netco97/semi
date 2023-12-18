package com.example.wk;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
	
	@Autowired
	private CommentMapper commentMapper;
	
    public List<CommentDTO> getCommentsByComposerIdWithPaging(Long composerId, int page, int size) {
        int offset = (page - 1) * size;
        return commentMapper.getCommentsByComposerIdWithPaging(composerId, offset, size);
    }

    public int countCommentsByComposerId(Long composerId) {
        return commentMapper.countCommentsByComposerId(composerId);
    }
	
	public List<CommentDTO> getAllCommentsByComposerId(Long composerId) {
		System.out.println(composerId);
		return commentMapper.getAllCommentsByComposerId(composerId);
	}
	
	public void insertComment(CommentDTO comment) {
		System.out.println(comment);
		commentMapper.insertComment(comment);
	}
	
	public void deleteCommentById(Long commentId) {
		System.out.println("CommentService : " + commentId);
		commentMapper.deleteComment(commentId);
	}

}
