package com.example.demo.th.music_comment;

import java.util.List;

import org.apache.catalina.connector.Request;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.SessionScope;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class MusicCommentService {

	private final HttpSession httpSession;

	private final MusicCommentMapper musicCommentMapper;

	public List<MusicCommentDTO> getComments(int song_id) {

		List<MusicCommentDTO> result = musicCommentMapper.getComments(song_id);

		return result;
	}

	public void regMusicComment(int song_id, String comment_text) {

		// 여기서 세션값으로 유저ID 를 빼와서 넣어놓을 예정

		String userFullPhoneNumber = httpSession.getAttribute("userFullPhoneNumber").toString();

		System.out.println("서비스 : " + song_id);

		musicCommentMapper.regComment(song_id, userFullPhoneNumber, comment_text);

	}

//	public  regMusicComment(int song_id, String inputComment) {
//		
//	}

}
