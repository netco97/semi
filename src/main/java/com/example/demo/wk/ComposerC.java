package com.example.demo.wk;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        System.out.println("(Server)currentPage: " + page);
        //model.addAttribute("pageSize", size);
        
        // 현재 페이지 주변의 페이지 번호를 계산하여 모델에 추가
        int maxPageToShow = 10; // 화면에 보여줄 최대 페이지 수
        int totalPages = (int) Math.ceil((double) totalComments / size);
        
        //int startPage = Math.max(1, page - maxPageToShow / 2);
        //int endPage = Math.min(totalPages, startPage + maxPageToShow - 1);
        
        int startPage = 1 + ((page - 1) / maxPageToShow) * maxPageToShow;
        int endPage = Math.min(totalPages, startPage + maxPageToShow - 1);
        
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("totalPages", totalPages);
        
        model.addAttribute("content", "wk/artist_detail");
        
        System.out.println("(Server)totalPages: " + totalPages);
        
        return "wk/index";
    }
}