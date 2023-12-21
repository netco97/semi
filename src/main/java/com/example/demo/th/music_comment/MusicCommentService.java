package com.example.demo.th.music_comment;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MusicCommentService {
	
	private final MusicCommentMapper musicCommentMapper;

	public List<MusicCommentDTO> getComments(int song_id) {
		
		
		
		List<MusicCommentDTO> result = musicCommentMapper.getComments(song_id);
		
		
		
		
		return result;
	}

	public void regMusicComment(int song_id, String comment_text) {
		
		// 여기서 세션값으로 유저ID 를 빼와서 넣어놓을 예정
		String fullphonenumber= "01066705590";
		System.out.println("서비스 : "+ song_id);
		
		
			musicCommentMapper.regComment(song_id,fullphonenumber,comment_text);
			
		
		
		
		
		
		
		
		
	}
	
//	public  regMusicComment(int song_id, String inputComment) {
//		
//	}

	
}
