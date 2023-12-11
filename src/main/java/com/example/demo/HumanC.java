package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class HumanC {

	private final TestService_Model testService_model;

	@GetMapping("/index")
	public String home(Model model) {
		model.addAttribute("hello", "hello");
		return "index";
	}
	@GetMapping("/getone")
	public String test() {
		Human h = testService_model.getHuman("1");
		System.out.println(h);
		return "NewFile";
	}
	@GetMapping("/getAll")
	public String test2(Model model) {
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
