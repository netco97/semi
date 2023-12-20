package com.example.demo.dy.follow;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class FollowController {
	@GetMapping("/follow")
	public String follow() {
        return "dw_view/follow";
    }
	
	
	
	

}
