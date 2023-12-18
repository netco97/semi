package com.example.wk;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class AccountC {
	
	@Autowired
	AccountService accountService;
    
	@GetMapping("/getAccountAll")
	public String accountAll() {
		List<AccountDTO> a = accountService.getAccountAll();
		for (AccountDTO accountDTO : a) {
			System.out.println(accountDTO);
		}
		return "wk/index";
	}
	
}

