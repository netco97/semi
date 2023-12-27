package com.example.demo.th.music_comment;

import java.util.Date;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MusicCommentDTOwithNickName {
	private int comment_id;
	private int song_id;
	private String userFullPhoneNumber;
	private String comment_text;
	private Date comment_date;
	private String user_nickName;
	
	
}
