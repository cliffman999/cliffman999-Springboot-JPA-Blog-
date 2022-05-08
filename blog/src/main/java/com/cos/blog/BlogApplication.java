package com.cos.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}

}
//Get요청 (데이터select)-> 주소에 데이터(key=value)를 담아서 보낸다, body로 데이터를 담아서 보내지 않는다.
//Post, Put, Delete요청 (데이터change)-> 데이터를 보내야할 양이 많음, 자바스크립트로 ajax요청 + json으로 데이터 통일
