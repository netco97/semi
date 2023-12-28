package com.example.demo.wk.artist_comment;

import java.time.LocalDateTime;
import lombok.Data;
import lombok.ToString;

@Data
public class ArtistCommentDTO {
    private Long comment_id;
    private String composer_id;
    private String userNickname;
    private String comment_content;
    private LocalDateTime created_at;
    
    public ArtistCommentDTO() {
    }
    
    public ArtistCommentDTO(String composer_id, String userNickname, String comment_content) {
    	this.composer_id = composer_id;
    	this.userNickname = userNickname;
    	this.comment_content = comment_content;
    	this.created_at = LocalDateTime.now();
    }
}