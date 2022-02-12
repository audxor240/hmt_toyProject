package com.cos.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

//	인증이 안된 사용자들이 출입할 수 있는 경로를 /auth/** 허용
// 그냥 주소가 / 이면 index.jsp 허용
//	static이하에 있는 /js/**, /css/**, /image/**

@Controller
public class UserContoller {
	
	@GetMapping("/auth/joinForm")
	public String joinForm() {
		return "user/joinForm";
	}
	
	@GetMapping("/user/userDetailForm")
	public String userDetailForm(Model model) {
		//boardService.글수정보기(id);
		//model.addAttribute("board",boardService.글상세보기(id));
		return "user/userDetailForm";
	}
	
	@GetMapping("/auth/loginForm")
	public String loginForm() {
		return "user/loginForm";
	}
}
