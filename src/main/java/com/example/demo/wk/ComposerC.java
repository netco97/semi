package com.example.demo.wk;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

// html에서 form요청이 들어오거나, 내가 url에 직접 입력한주소(즉 다른페이지에서 어떠한 항목을 누를때자바스크립트단에서 바뀌는 url--> <a>태그의 href 또는 자바스크립트의 온클릭등) 
// 그에 해당하는 주소에 @GetMapping 하면, view로 쏠 수 있음. 

// RestController의 역할은 비동기통신(ajax, fetch등 ) 이런 요청의 주소값이 들어왔을때 프론트단의 js에서 성공(suceess)했을 경우 그부분에서 사용하기 위해서 우리가 컨트롤러에서
// 넘겨주는 

@Controller
@RequiredArgsConstructor
public class ComposerC {

	private final ComposerService composerService;

	@GetMapping("/artist_detail/{id}")
	public String getComposerById(@PathVariable Long id, Model model) {
		ComposerDTO composer = composerService.getComposerById(id);

		// 이미지 파일의 경로 설정 (기본 이미지 포함)
		composer.setImg(composer.getImgOrDefault());

		model.addAttribute("composer", composer);
		System.out.println(composer);

		model.addAttribute("content", "wk/artist_detail");

		return "wk/index";
	}

	@GetMapping("artist_reg")
	public String artist_reg(Model model) {
		model.addAttribute("content", "wk/artist_reg");
		return "wk/index";
	}

	@PostMapping("artist_reg/upload")
	public String uploadArtist(@ModelAttribute ComposerDTO composer, Model model, HttpSession session) {
	    composerService.insertComposer(composer);
	    model.addAttribute("content", "wk/artist_reg");
	    System.out.println(composer);
	    return "wk/index";
	}
}