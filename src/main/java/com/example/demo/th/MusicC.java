package com.example.demo.th;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MusicC {

	private final MusicService musicService;

	@GetMapping("/musicMenu")
	  public String musicMenu(Model model) {
        
        // TagsDTO 는 이미 각각의 배열임;
        // mybatis에서 매핑하면 그값을 dto의 배열에 저장함
        // 그러면 TagsDTO가 사실 끝임
        // 이걸 List로 만드는 필요가 없음
        
        
        TagsDTO tags = new TagsDTO();
        tags = (TagsDTO) musicService.getTags();
        System.out.println("c test" + tags);
        model.addAttribute("tags", tags);
        
        return "th/musicMenu";
    }

	@GetMapping("/musicDetail")
	public String musicDetail() {
		return "th/musicDetail";
	}
}
