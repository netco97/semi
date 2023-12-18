package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;



@Controller
public class HumanC {

	@Autowired
	TestService_Model testService_model;	
	
	@GetMapping("/")
	public String home(Model model, HttpServletRequest req) {
		model.addAttribute("hello", "hello");
		
//		String protocol = req.getProtocol();
//		String scheme = req.getScheme();
//		return scheme.equals("http") ? "index" : "test";
		return "index";
	}
	
	@GetMapping("/getone")
	public String test() {
		Human h = testService_model.getHuman("1");
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