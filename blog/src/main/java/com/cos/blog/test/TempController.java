package com.cos.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller //해당경로 이하에 있는 파일을 리턴
public class TempController {
	
	//http://localhost:8000/blog/temp/home
	@GetMapping("/temp/home")
	public String tempHome() {
		System.out.println("tempHome()");
		//파일리턴 기본경로: src/main/resources/static
		//리턴명: /home/html
		//풀경로: src/main/resources/static (+) /home.html
		return "/home.html";
	}
	
	//http://localhost:8000/blog/temp/img
	@GetMapping("/temp/img")
	public String templmg() {
		return "/a.png";
	}
	
	//http://localhost:8000/blog/temp/jsp
	@GetMapping("/temp/jsp")
	public String tempJsp() {
		//prefix: /WEB-INF/views/
		//suffix: .jsp
		//풀경로: /WEB-INF/views/test.jsp
		return "test";
	}
}
