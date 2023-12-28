package com.example.demo.wk.artist_comment;


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

import com.example.demo.wk.ComposerService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RestController // JSON 형태로 객체 데이터를 반환
@RequestMapping("/comments")
@RequiredArgsConstructor
public class ArtistCommentC {
	
	private final ArtistCommentService commentService;
	
	// 아티스트 코멘트 페이징
    @GetMapping("/{composer_id}")
    public ArtistCommentResponseDTO getAllCommentsByComposerIdWithPaging(
            @PathVariable String composer_id,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {

    	List<ArtistCommentDTO> comments = commentService.getCommentsByComposerIdWithPaging(composer_id, page, size);
        int totalComments = commentService.countCommentsByComposerId(composer_id);
        
        ArtistCommentResponseDTO responseDTO = new ArtistCommentResponseDTO();
        responseDTO.setComments(comments);
        responseDTO.setTotalComments(totalComments);
        responseDTO.setPage(page);
        responseDTO.setTotalPages((int) Math.ceil((double) totalComments / size));
        
        System.out.println(comments);
        System.out.println(totalComments);
        
        return responseDTO;
    }
    
    // 아티스트 코멘트 작성
    @PostMapping
    public List<ArtistCommentDTO> insertComment(@RequestBody ArtistCommentDTO comment, Model model) {
    	
//    	String userFullPhoneNumber = (String) session.getAttribute("userFullPhoneNumber");
//        comment.setComposer_id(userFullPhoneNumber);
        
    	commentService.insertComment(comment);
    	List<ArtistCommentDTO> comments = commentService.getAllCommentsByComposerId(comment.getComposer_id());
    	
    	System.out.println("CommentId: " + comment.getComment_id());
    	System.out.println("ComposerId: " + comment.getComposer_id());
    	System.out.println("UserNickName: " + comment.getUserNickname());
    	System.out.println("CommentContent: " + comment.getComment_content());
    	System.out.println("CreatedAt: " + comment.getCreated_at());
    	
    	System.out.println(comment);
    	
        return comments;
    }
    
    // 아티스트 코멘트 삭제
    @DeleteMapping("/{comment_id}")
    public ResponseEntity<String> deleteCommentById(@PathVariable Long comment_id) {
        try {
            commentService.deleteCommentById(comment_id);
            System.out.println("comment_id" + comment_id);
            return new ResponseEntity<>("Comment deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
        	e.printStackTrace();
        	System.out.println("comment_id" + comment_id);
            return new ResponseEntity<>("Error deleting comment", HttpStatus.INTERNAL_SERVER_ERROR);
            
        }
    }
}
