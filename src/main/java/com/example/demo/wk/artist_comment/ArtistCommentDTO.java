package com.example.demo.wk.artist_comment;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ArtistCommentDTO {
    private Long commentId;
    private Long composerId;
    private String userNickname;
    private String commentContent;
    private LocalDateTime createdAt;
    
    public ArtistCommentDTO() {
    }
    
    public ArtistCommentDTO(Long composerId, String userNickname, String commentContent) {
    	this.composerId = composerId;
    	this.userNickname = userNickname;
    	this.commentContent = commentContent;
    }
    
}


