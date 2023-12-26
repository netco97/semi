package com.example.demo.wk.artist_comment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ArtistCommentService {
	
	private final ArtistCommentMapper commentMapper;
	
	// 코멘트 페이징
    public List<ArtistCommentDTO> getCommentsByComposerIdWithPaging(Long composerId, int page, int size) {
        int offset = (page - 1) * size;
        return commentMapper.getCommentsByComposerIdWithPaging(composerId, offset, size);
    }

    // 코멘트 카운트
    public int countCommentsByComposerId(Long composerId) {
        return commentMapper.countCommentsByComposerId(composerId);
    }
	
    // 코멘트 조회
	public List<ArtistCommentDTO> getAllCommentsByComposerId(Long composerId) {
		System.out.println(composerId);
		return commentMapper.getAllCommentsByComposerId(composerId);
	}
	
	// 코멘트 입력
	public void insertComment(ArtistCommentDTO comment) {
		System.out.println(comment);
		commentMapper.insertComment(comment);
	}
	
	// 코멘트 삭제
	public void deleteCommentById(Long commentId) {
		System.out.println("CommentService : " + commentId);
		commentMapper.deleteComment(commentId);
	}

}
