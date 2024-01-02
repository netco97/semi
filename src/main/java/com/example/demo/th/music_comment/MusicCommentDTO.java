package com.example.demo.th.music_comment;

import java.time.LocalDateTime;
import java.util.Date;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MusicCommentDTO {

	private int comment_id;
	private int song_id;
	private String userFullPhoneNumber;
	private String comment_text;
	private LocalDateTime comment_date;

}
