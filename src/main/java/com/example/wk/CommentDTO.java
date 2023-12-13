package com.example.wk;

import java.sql.Timestamp;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CommentDTO {
    private Long commentId;
    private Long composerId;
    private String userName;
    private String content;
    private Timestamp createdAt;

    public CommentDTO() {
    	
    }
}

