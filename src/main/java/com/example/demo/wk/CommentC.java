package com.example.demo.wk;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController // JSON 형태로 객체 데이터를 반환
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentC {
	
	private final CommentService commentService;
	//private final ComposerService composerService;
	
//	@GetMapping("{composerId}")
//	public List<CommentDTO> getAllCommentsByComposerId(@PathVariable Long composerId) {
//		System.out.println(composerId);
//		return commentService.getAllCommentsByComposerId(composerId);
//	}
	
    @GetMapping("{composerId}")
    public List<CommentDTO> getAllCommentsByComposerIdWithPaging(
            @PathVariable Long composerId,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {

        int totalComments = commentService.countCommentsByComposerId(composerId);
        List<CommentDTO> comments = commentService.getCommentsByComposerIdWithPaging(composerId, page, size);

        // 페이징 정보를 클라이언트에 전달하려면 필요에 따라 처리
        System.out.println(totalComments);
        System.out.println(comments);
        
        return comments;
    }
    	
    @PostMapping
    public void insertComment(@RequestBody CommentDTO comment, Model model) {
    	System.out.println("CommentId: " + comment.getCommentId());
    	System.out.println("ComposerId: " + comment.getComposerId());
    	System.out.println("UserName: " + comment.getUserName());
    	System.out.println("CommentContent: " + comment.getCommentContent());
    	System.out.println("CreatedAt: " + comment.getCreatedAt());
    	
        commentService.insertComment(comment);
        
        List<CommentDTO> comments = commentService.getAllCommentsByComposerId(comment.getComposerId());
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
}
