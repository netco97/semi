package com.example.demo.th.MusicPaging;

import java.util.List;

import com.example.demo.th.music_comment.MusicCommentDTO;
import com.example.demo.th.music_comment.MusicCommentDTOwithNickName;

import lombok.Data;
import lombok.ToString;


@Data
@ToString
public class SongCommentResponseDTO {
	private List<MusicCommentDTOwithNickName> comments; // 아티스트 코멘트의 목록
	private int totalComments; // 전체 코멘트의 수
	private int page; // 현재 페이지
	private int totalPages; // 전체 페이지 수

}
