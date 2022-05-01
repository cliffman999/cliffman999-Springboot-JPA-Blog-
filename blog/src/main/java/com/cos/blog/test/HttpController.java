package com.cos.blog.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController //문자 그 자체를 리턴
public class HttpController {
	
	private static final String TAG = "HttpController : ";
	
	//http://localhost:8000/blog/http/lombok
	@GetMapping("/http/lombok")
	public String lombokTest() {
		Member m = Member.builder().username("saar").password("1234").email("vkqkak@naver.com").build();
		System.out.println(TAG+"getter : "+m.getUsername());
		m.setUsername("jin");
		System.out.println(TAG+"setter : "+m.getUsername());
		return "lombok test complete";
	}
	
	//인터넷 브라우저 요청은 get밖에 할 수가 없다.
	//http://localhost:8000/blog/http/get (select)
	@GetMapping("/http/get")
	public String getTest(Member m) { //MessageConverter
		return "get 요청 : " + m.getId() + ", " + m.getUsername() + ", " + m.getPassword() + ", " + m.getEmail();
	}
	
	//http://localhost:8080/http/post (insert)
	@PostMapping("http/post")
	public String postTest(@RequestBody Member m) {
		return "post 요청 : " + m.getId() + ", " + m.getUsername() + ", " + m.getPassword() + ", " + m.getEmail();
	}
	
	//http://localhost:8080/http/put (update)
	@PutMapping("http/put")
	public String putTest(@RequestBody Member m) {
		return "put 요청" + m.getId() + ", " + m.getUsername() + ", " + m.getPassword() + ", " + m.getEmail();
	}
	
	//http://localhost:8080/http/delete (delete)
	@PutMapping("http/delete")
	public String deleteTest() {
		return "delete 요청";
	}
}
