package com.example.demo.dy.detail;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ArtistDetailController {

	@GetMapping("/detail")
	public String detail() {
		return "dw_view/artist_detail";
		
		
	}
	
}
