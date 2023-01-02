package com.cos.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller	//해당경로의 파일을 리턴하지만, @RestController는 리턴할때 함수의 자료형에 따라 리턴한다...
public class TempControllerTest {
	
	// http://localhost:8000/blog/temp/home
	@GetMapping("/temp/home")
	public String tempHome() {
		System.out.println("temphome()");
		// 파일리턴 기본경로 : src/main/resources/static
		// 리턴명 : /home.html 로 해야 경로를 찾을수 있다.
		// 풀경로 : src/main/resources/static/home.html
		return "/home.html";
	}
	
	@GetMapping("/temp/jsp")
	public String tempJsp() {
		// prefix : /WEB-INF/views/
		// suffix : .jsp
		// 풀경로 : /WEB-INF/views//test.jsp.jsp
		return "test";
	}
}
