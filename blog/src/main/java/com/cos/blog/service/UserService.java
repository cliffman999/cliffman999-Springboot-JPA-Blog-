package com.cos.blog.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;


//트랜잭션 : 일이 처리되기위한 가장 작은 단위, 트랜잭션들이 묶여서 하나의 트랜잭션이 될 수 있다. 
//서비스 : 여러개의 트랜잭션이 묶여서 동작을 수행하는것
@Service //스프링이 컴포넌트 스캔을 통해 빈에 등록(IOC)
public class UserService {
	
	@Autowired
	private UserRepository userRepository; 
	
	 //트랜잭션화 밑의 수행들이 실패가되면 ROLLBACK 성공하면 COMMIT이 수행됨
	@Transactional
	public void 회원가입(User user) {
		userRepository.save(user);
	}
	
	@Transactional(readOnly=true) //select할때 트랜잭션 시작, 서비스종료시에 트랜잭션종료 (정합성 유지)
	public User 로그인(User user) {
		return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
	}

}
