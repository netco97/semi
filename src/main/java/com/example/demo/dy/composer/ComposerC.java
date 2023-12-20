package com.example.demo.dy.composer;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dy.comment.CommentDTO;
import com.example.demo.dy.comment.CommentService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/artist_detail")
@RequiredArgsConstructor
public class ComposerC {
	
	private final ComposerService composerService;
	private final CommentService commentService;
	
    @GetMapping("{id}")
    public String getComposerById(@PathVariable Long id,
    		@RequestParam(name = "page", defaultValue = "1") int page,
    		Model model) {
        ComposerDTO composer = composerService.getComposerById(id);
        
        //List<CommentDTO> comments = commentService.getAllCommentsByComposerId(id);
        
        // 페이징 관련 변수
        int size = 10; // 한 페이지에 보여질 댓글 수

        // 페이지 번호를 받아오는 로직이 있다면 이를 활용하여 page 변수 설정

        List<CommentDTO> comments = commentService.getCommentsByComposerIdWithPaging(id, page, size);
        int totalComments = commentService.countCommentsByComposerId(id);
        
        // 이미지 파일의 경로 설정 (기본 이미지 포함)
        composer.setImg(composer.getImgOrDefault());
        
        model.addAttribute("composer", composer);
        model.addAttribute("comments", comments);
        model.addAttribute("totalComments", totalComments);
        
        System.out.println(composer);
        System.out.println(comments);
        
        // 페이징 정보를 클라이언트에 전달
        model.addAttribute("currentPage", page);
        model.addAttribute("pageSize", size);
        model.addAttribute("totalPages", (int) Math.ceil((double) totalComments / size));
        
        model.addAttribute("content", "dw_view/artist_detail");
        return "dw_view/index";
    }
}