package com.cos.blog.test;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@RestController //html이 아니라 data를 리턴해주는 controller
public class DummyController {
	
	@Autowired //의존성주입(DI)
	private UserRepository userRepository;
	
	@DeleteMapping("/dummy/user/{id}")
	public String delete(@PathVariable int id) {
		try {
			userRepository.deleteById(id);
		} catch(EmptyResultDataAccessException e) {
			return "삭제에 실패하였습니다. 해당 ID는 DB에 없습니다";
		}
		return "삭제완료 id: "+id;
	}
	
	//email,password수정
	@Transactional //함수종료시에 자동 commit //commit값이 영속성컨텍스트의 값과 다르면 자동으로 DB를 수정 --> 더티체킹
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User requestUser) {
		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("수정에 실패하였습니다");
		});
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());
		
		//userRepository.save(user); //save함수는 id를 전달하지 않거나 id가 없으면 insert를해주고
								     //해당 id에 대한 데이터가 존재하면 update를해준다, 또한 객체를 전달할때 값이 존재하지 않으면 null값을 전달한다.
		return user;
	}

//try, catch문을 이용한 update
//	//email,password수정
//	@Transactional //함수종료시에 자동 commit //commit값이 영속성컨텍스트의 값과 다르면 자동으로 DB를 수정 --> 더티체킹
//	@PutMapping("/dummy/user/{id}")
//	public User updateUser(@PathVariable int id, @RequestBody User requestUser) {
//		System.out.println("id : "+id);
//		System.out.println("password : "+requestUser.getPassword());
//		System.out.println("email : "+requestUser.getEmail());
//		User user = requestUser;
//		try {
//			user = userRepository.findById(id).get();
//			user.setPassword(requestUser.getPassword());
//			user.setEmail(requestUser.getEmail());
//		} catch(Exception e) {
//			return requestUser; 
//		}
//		//userRepository.save(user); //save함수는 id를 전달하지 않거나 id가 없으면 insert를해주고
//								     //해당 id에 대한 데이터가 존재하면 update를해준다, 또한 객체를 전달할때 값이 존재하지 않으면 null값을 전달한다.
//		return user;
//	}
	
	//http://localhost:8000/blog/dummy/user
	@GetMapping("/dummy/users")
	public List<User> list() {
		return userRepository.findAll();
	}
	
	//한페이지당 2건의 데이터를 리턴, ?page=숫자 로 원하는 페이지 선택가능
	//http://localhost:8000/blog/dummy/user
	@GetMapping("/dummy/user")
	public List<User> pageList(@PageableDefault(size=2,sort="id",direction = Sort.Direction.DESC)Pageable pageable) {
		List<User> users = userRepository.findAll(pageable).getContent();
		return users;
	}
	
	//{id} 주소로 파라미터를 전달받을 수 있음
	//http://localhost:8000/blog/dummy/user/{id}
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			public IllegalArgumentException get() {
				// TODO Auto-generated method stub
				return new IllegalArgumentException("해당 유저는 없습니다 id : " + id);
			}
		});
		//요청:웹브라우저
		//user객체:자바 오브젝트
		//변환(웹브라우저가 이해할 수 있는 데이터) -> json
		//스프링부트 = MessageConverter가 응답시에 자동작동 
		//자바오브젝트를 리턴하게되면  MessageConveter가 Jackson라이브러리를 호출한뒤 자바오브젝트를 json으로 변환해서 브라우저에게 전송
		//브라우저는 json파일을 자기포맷에 맞게 변환해서 사용
		return user;
	}
	
	@PostMapping("/dummy/join")
	public String join(User user) { //key=value
		user.setRole(RoleType.USER);
		userRepository.save(user);
		return "회원가입이 완료되었습니다";
	}
}
