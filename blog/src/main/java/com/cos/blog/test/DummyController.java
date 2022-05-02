package com.cos.blog.test;

import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@RestController //html이 아니라 data를 리턴해주는 controller
public class DummyController {
	
	@Autowired //의존성주입(DI)
	private UserRepository userRepository;
	
	//{id} 주소로 파라미터를 전달받을 수 있음
	//http://localhost:8000/blog/dummy/user/3 --> id에 3이 들어감
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			public IllegalArgumentException get() {
				// TODO Auto-generated method stub
				return new IllegalArgumentException("해당 유저는 없습니다 id : " + id);
			}
		});
		//요청:웹브라우저(RestController)
		//user객체:자바 오브젝트
		//변환(웹브라우저가 이해할 수 있는 데이터) -> json
		//스프링부트 = MessageConverter라는 친구가 응답시에 자동작동 
		//자바오브젝트를 리턴하게되면 Jackson라이브러리를 호출한뒤 자바오브젝트를 json으로 변환해서 브라우저에게 전송
		return user;
	}
	
	@PostMapping("/dummy/join")
	public String join(User user) { //key=value
		System.out.println("username : "+user.getUsername());
		System.out.println("password : "+user.getPassword());
		System.out.println("email : "+user.getEmail());
		
		user.setRole(RoleType.USER);
		userRepository.save(user);
		return "회원가입이 완료되었습니다";
	}
}
