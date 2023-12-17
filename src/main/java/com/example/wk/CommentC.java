package com.example.wk;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // JSON 형태로 객체 데이터를 반환
@RequestMapping("/comments")
public class CommentC {
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private ComposerService composerService;
	
	@GetMapping("{composerId}")
	public List<CommentDTO> getAllCommentsByComposerId(@PathVariable Long composerId) {
		System.out.println(composerId);
		return commentService.getCommentsByComposerId(composerId);
	}
    	
    @PostMapping
    public void insertComment(@RequestBody CommentDTO comment, Model model) {
    	System.out.println("CommentId: " + comment.getCommentId());
    	System.out.println("ComposerId: " + comment.getComposerId());
    	System.out.println("UserName: " + comment.getUserName());
    	System.out.println("CommentContent: " + comment.getCommentContent());
    	System.out.println("CreatedAt: " + comment.getCreatedAt());
    	
        commentService.insertComment(comment);
        
        List<CommentDTO> comments = commentService.getCommentsByComposerId(comment.getComposerId());
        model.addAttribute("comments", comments);
        System.out.println(comments);
    }
    
    // 코멘트 삭제
    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteCommentById(@PathVariable Long commentId) {
        try {
            commentService.deleteCommentById(commentId);
            System.out.println(commentId);
            return new ResponseEntity<>("Comment deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
        	e.printStackTrace();
        	System.out.println(commentId);
            return new ResponseEntity<>("Error deleting comment", HttpStatus.INTERNAL_SERVER_ERROR);
            
        }
    }
    
//insertComment 메서드가 왜 void를 반환하고 있는지에 대해 살펴보겠습니다.
//일반적으로 void를 반환하는 메서드는 해당 메서드가 작업을 수행하고 호출자에게 반환할 값이 없음을 의미합니다.
//insertComment 메서드는 댓글을 데이터베이스에 추가하는 작업을 수행하고,
//특별히 호출자에게 반환할 값이 없는 상황입니다.
//데이터베이스에 삽입된 데이터의 ID나 다른 정보가 필요하다면 이를 반환하도록 설계할 수 있습니다.

}
