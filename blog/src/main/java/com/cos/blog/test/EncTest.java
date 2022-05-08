package com.cos.blog.test;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncTest {
	
	@Test
	public void 해쉬_암호화() {
		String encPasswordString = new BCryptPasswordEncoder().encode("1234");
		System.out.println("해쉬 변환 값 : "+encPasswordString);
	}
}
