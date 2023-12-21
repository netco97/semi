package com.example.demo.th.music_comment;

import java.util.Date;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MusicCommentDTO {

	private int comment_id;
	private int song_id;
	private String fullphonenumber;
	private String comment_text;
	private Date comment_date;
}
