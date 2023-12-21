package com.example.demo.wk;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeC {

	private final AccountMapper accountMapper;
	
	
	public HomeC(AccountMapper accountMapper) {
		this.accountMapper = accountMapper;
	}

    @GetMapping("/")
    public String home(Model model) {
        // 중간 페이지 내용을 설정
        model.addAttribute("content", "wk/home");
        return "wk/index";
    }
    
    @GetMapping("/about")
    public String about(Model model) {
        // 중간 페이지 내용을 설정
        model.addAttribute("content", "wk/about");
        return "wk/index";
    }
    
	@GetMapping("artist_reg")
	public String artist_reg(Model model) {
		model.addAttribute("content", "wk/artist_reg");
		return "wk/index";
	}
	
	@GetMapping("artist_update")
	public String artist_update(Model model) {
		model.addAttribute("content", "wk/artist_update");
		return "wk/index";
	}
	
	@GetMapping("/music_upload")
	public String music_upload(Model model) {
		model.addAttribute("content", "wk/music_upload");
		return "wk/index";
	}
	
	@GetMapping("/mypage_artist")
	public String mypage_artist(Model model) {
		model.addAttribute("content", "wk/mypage_artist");
		return "wk/index";
	}
	
	@GetMapping("/mypage_user")
	public String mypage_user(Model model) {
		model.addAttribute("content", "wk/mypage_user");
		return "wk/index";
	}
	
    /*@PostMapping("/login")
    public String login(@RequestParam String id, @RequestParam String password, HttpSession session, Model model) {
        // 간단한 로그인 로직: 예시로 username이 "user", password가 "password"인 경우에 로그인 성공으로 가정
    	AccountDTO accountDTO = accountMapper.login(id, password);
        if (accountDTO != null && password.equals(accountDTO.getA_pw())) {
            // 세션에 로그인 정보 저장
            session.setAttribute("id", id);
            session.setAttribute("name", accountDTO.getA_name());
            model.addAttribute("content", "wk/home");
            return "wk/index";
            //return "wk/index"; // 로그인 성공 시 이동할 페이지
        } else {
            model.addAttribute("error", "Invalid username or password");
            model.addAttribute("content", "wk/home");
            return "wk/index"; // 로그인 실패 시 로그인 페이지로 다시 이동
        }
    }
    // 로그아웃 처리
    @PostMapping("/logout")
    public String logout(HttpSession session, Model model) {
        // 세션에서 로그인 정보 삭제
        session.removeAttribute("id");
        model.addAttribute("content", "wk/home");
        return "wk/index"; // 로그아웃 시 이동할 페이지
    }
    */
}
