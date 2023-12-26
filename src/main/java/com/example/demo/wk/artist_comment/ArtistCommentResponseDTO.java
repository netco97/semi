package com.example.demo.wk.artist_comment;

import java.util.List;

import lombok.Data;

@Data
public class ArtistCommentResponseDTO {
	private List<ArtistCommentDTO> comments;  // 아티스트 코멘트의 목록
    private int totalComments;  // 전체 코멘트의 수
    private int page;  // 현재 페이지
    private int totalPages;  // 전체 페이지 수
}
