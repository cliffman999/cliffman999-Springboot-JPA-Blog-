package com.cos.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cos.blog.model.User;

//DAO
//자동으로 빈으로 등록이 된다.(@Repository 생략가능)
//JpaRepository는 인터페이스를 준비하기만 하면, 자동으로 클래스를 만들고 Bean을 생성하는 것이다. 필요한 것은 인터페이스 뿐이다
@Repository								
public interface UserRepository extends JpaRepository<User, Integer>{
	
	//SELECT * FROM user WHERE username=1?;
	Optional<User> findByUsername(String username); //네이밍 쿼리
	
}
//JPA 네이밍 쿼리 
	//쿼리에 맞게 만들면 SELECT * FROM user WHERE username=?1 AND password=?2;
	//User findByUsernameAndPassword(String username, String password);
	
	//위와 동일한 로직 수행
	//@Query(value="SELECT * FROM user WHERE username=?1 AND password=?2", nativeQuery = true)
	//User login(String username, String password);
	