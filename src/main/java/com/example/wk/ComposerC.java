package com.example.wk;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/artist_detail")
public class ComposerC {
	
	@Autowired
	private ComposerService composerService;
	
	@Autowired
	private CommentService commentService;
	
    @GetMapping("{id}")
    public String getComposerById(@PathVariable Long id, Model model) {
        ComposerDTO composer = composerService.getComposerById(id);
        List<CommentDTO> comments = commentService.getCommentsByComposerId(id);
        
        // 이미지 파일의 경로 설정 (기본 이미지 포함)
        composer.setImg(composer.getImgOrDefault());
        
        model.addAttribute("composer", composer);
        model.addAttribute("comments", comments);
        System.out.println(composer);
        System.out.println(comments);
        model.addAttribute("content", "wk/artist_detail");
        return "wk/index";
    }
}