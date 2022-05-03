package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cos.blog.model.User;

//DAO
//자동으로 빈으로 등록이 된다.(@Repository 생략가능)
@Repository								
public interface UserRepository extends JpaRepository<User, Integer>{
									  //JpaRepository는 인터페이스를 준비하기만 하면, 자동으로 클래스를 만들고 Bean을 생성하는 것이다. 필요한 것은 인터페이스 뿐이다
}
