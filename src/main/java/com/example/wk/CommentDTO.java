package com.example.wk;



import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CommentDTO {
    private Long commentId;
    private Long composerId;
    private String userName;
    private String commentContent;
    private LocalDateTime createdAt;
    
    public CommentDTO() {
    	
    }
    
    public CommentDTO(Long composerId, String userName, String commentContent) {
    	this.composerId = composerId;
    	this.userName = userName;
    	this.commentContent = commentContent;
    	this.createdAt = LocalDateTime.now();
    }
    
    public String getFormattedCreatedAt() {
        if (createdAt != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            return createdAt.format(formatter);
        }
        return ""; // 또는 다른 기본값을 리턴할 수 있음
    }
    
}



