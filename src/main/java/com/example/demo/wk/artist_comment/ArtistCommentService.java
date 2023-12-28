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
    public List<ArtistCommentDTO> getCommentsByComposerIdWithPaging(String composer_id, int page, int size) {
        int offset = (page - 1) * size;
        return commentMapper.getCommentsByComposerIdWithPaging(composer_id, offset, size);
    }

    // 코멘트 카운트
    public int countCommentsByComposerId(String composer_id) {
        return commentMapper.countCommentsByComposerId(composer_id);
    }
	
    // 코멘트 조회
	public List<ArtistCommentDTO> getAllCommentsByComposerId(String composer_id) {
		System.out.println(composer_id);
		return commentMapper.getAllCommentsByComposerId(composer_id);
	}
	
	// 코멘트 입력
	public void insertComment(ArtistCommentDTO comment) {
		System.out.println(comment);
		commentMapper.insertComment(comment);
	}
	
	// 코멘트 삭제
	public void deleteCommentById(Long comment_id) {
		commentMapper.deleteComment(comment_id);
	}

}
