package com.example.demo.th.music_comment;

import java.util.List;

import org.apache.catalina.connector.Request;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.SessionScope;

import com.example.demo.wk.artist_comment.ArtistCommentDTO;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class MusicCommentService {

	private final HttpSession httpSession;

	private final MusicCommentMapper musicCommentMapper;

	public List<MusicCommentDTOwithNickName> getComments(int song_id) {

		List<MusicCommentDTOwithNickName> result = musicCommentMapper.getComments(song_id);

		return result;
	}

	public void regMusicComment(int song_id, String comment_text) {

		// 여기서 세션값으로 유저ID 를 빼와서 넣어놓을 예정

		String userFullPhoneNumber = httpSession.getAttribute("userFullPhoneNumber").toString();

//		System.out.println("서비스 : " + song_id);

		musicCommentMapper.regComment(song_id, userFullPhoneNumber, comment_text);

	}
	
	public String getUserNickName(String userFullPhoneNumber) {
		
		
		String userNickName = musicCommentMapper.getUserNickName(userFullPhoneNumber);
		
		
		return userNickName;
		
		
	}

	public List<MusicCommentDTOwithNickName> getCommentsBySongIdWithPaging(int song_id, int page, int size) {
		 int offset = (page - 1) * size;
	        return musicCommentMapper.getCommentsBySongIdWithPaging(song_id, offset, size);
	}

	public int countCommentsBySongId(int song_id) {
		
		
		  return musicCommentMapper.countCommentsBySongId(song_id);
	}

	public void deleteMusicComment(int comment_id) {
			
		
		
		System.out.println("딜리트가 됫니 ? " + musicCommentMapper.deleteMusicComment(comment_id)); 
		
	}

	

}
