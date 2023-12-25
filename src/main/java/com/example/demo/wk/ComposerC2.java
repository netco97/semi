//package com.example.demo.wk;
//
//import java.util.List;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import jakarta.servlet.http.HttpSession;
//import lombok.RequiredArgsConstructor;
//
//// html에서 form요청이 들어오거나, 내가 url에 직접 입력한주소(즉 다른페이지에서 어떠한 항목을 누를때자바스크립트단에서 바뀌는 url--> <a>태그의 href 또는 자바스크립트의 온클릭등) 
//// 그에 해당하는 주소에 @GetMapping 하면, view로 쏠 수 있음. 
//
//// RestController의 역할은 비동기통신(ajax, fetch등 ) 이런 요청의 주소값이 들어왔을때 프론트단의 js에서 성공(suceess)했을 경우 그부분에서 사용하기 위해서 우리가 컨트롤러에서
//// 넘겨주는 
//
//
//@Controller
//@RequestMapping("/artist_detail")
//@RequiredArgsConstructor
//public class ComposerC2 {
//	
//	private final ComposerService composerService;
//	
//    @GetMapping("{id}")
//    public String getComposerById(@PathVariable Long id,
//    		@RequestParam(name = "page", defaultValue = "1") int page,
//    		Model model) {
//        ComposerDTO composer = composerService.getComposerById(id);
//        
//        // 페이징 관련 변수
//        int size = 10; // 한 페이지에 보여질 댓글 수
//
//        // 페이지 번호를 받아오는 로직이 있다면 이를 활용하여 page 변수 설정
//        List<CommentDTO> comments = commentService.getCommentsByComposerIdWithPaging(id, page, size);
//        int totalComments = commentService.countCommentsByComposerId(id);
//        
//        // 이미지 파일의 경로 설정 (기본 이미지 포함)
//        composer.setImg(composer.getImgOrDefault());
//        
//        model.addAttribute("composer", composer);
//        model.addAttribute("comments", comments);
//        model.addAttribute("totalComments", totalComments);
//        System.out.println(composer);
//        System.out.println(comments);
//        
//        // 페이징 정보를 클라이언트에 전달
//        model.addAttribute("currentPage", page);
//        //model.addAttribute("pageSize", size);
//        
//        // 현재 페이지 주변의 페이지 번호를 계산하여 모델에 추가
//        int maxPageToShow = 10; // 화면에 보여줄 최대 페이지 수
//        int totalPages = (int) Math.ceil((double) totalComments / size);
//        
//        //int startPage = Math.max(1, page - maxPageToShow / 2);
//        //int endPage = Math.min(totalPages, startPage + maxPageToShow - 1);
//        int startPage = 1 + ((page - 1) / maxPageToShow) * maxPageToShow;
//        int endPage = Math.min(totalPages, startPage + maxPageToShow - 1);
//        
//        // test
//        if (page > 10) {
//			startPage = 11;
//			endPage = 20;
//		}
//        
//        model.addAttribute("startPage", startPage);
//        model.addAttribute("endPage", endPage);
//        model.addAttribute("totalPages", totalPages);
//        
//        model.addAttribute("content", "wk/artist_detail");
//        
//        System.out.println("(Server)currentPage: " + page);
//        System.out.println("(Server)startPage: " + startPage);
//        System.out.println("(Server)endPage: " + endPage);
//        System.out.println("(Server)totalPages: " + totalPages);
//        
//        return "wk/index";
//    }
//}