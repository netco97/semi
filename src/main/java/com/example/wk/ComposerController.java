package com.example.wk;

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
public class ComposerController {
	
	@Autowired
	private ComposerService composerService;
	
//	@GetMapping("/{id}")
//	public ResponseEntity<ComposerDTO> getComposerById(@PathVariable Long id) {
//		ComposerDTO composerDTO = composerService.getComposerById(id);
//		return new ResponseEntity<>(composerDTO, HttpStatus.OK);
//	}
	
    @GetMapping("/{id}")
    public String getComposerById(@PathVariable Long id, Model model) {
        ComposerDTO composerDTO = composerService.getComposerById(id);
        model.addAttribute("composerDTO", composerDTO);
        return "wk/artist_detail"; // 뷰 이름 리턴
    }
}


