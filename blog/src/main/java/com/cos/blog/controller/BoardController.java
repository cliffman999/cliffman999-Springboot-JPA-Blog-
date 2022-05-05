package com.cos.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller //data를 리턴해주는 Controller
public class BoardController {
	
	@GetMapping({"","/"}) 
	public String index() {
		//WEB-INF/views/index.jsp
		return "index";
	}
}
