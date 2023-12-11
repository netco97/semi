package com.example.wk;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//@Controller
//@RestController

@Controller
public class HumanC {

	@Autowired
	TestService_Model testService_model;	
	
	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("hello", "hello");
		return "index";
	}
	
	@GetMapping("/getone")
	public String test() {
		Human h = testService_model.getHuman("20");
		System.out.println(h);
		return "NewFile";
	}
	@GetMapping("/getAll")
	public String test2() {
		List<Human> h = testService_model.getAllHuman();
		for (Human human : h) {
			System.out.println(human);
		}
		return "NewFile";
	}
	
	@GetMapping("/reg")
	public String reg(Human h) {
		testService_model.regHuman(h);
		
		return "NewFile";
	}
}