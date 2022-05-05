package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //setter getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@DynamicInsert //insert시에 null인 필드를 제외시켜주는 어노테이션
//ORM -> Java Object(다른 언어도 포함) -> 테이블로 매핑해주는 기술
@Entity //User 클래스가 MySQL에 테이블이 생성이 된다.
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)//프로젝트에서 연결된 DB의 넘버링 전략을 따라간다. mysql의 경우 auto_increment
	private int id; //auto_increment로 넘버링할 계획
	
	@Column(nullable=false, length=30, unique=true)
	private String username;
	
	@Column(nullable=false, length=100)//암호화된 비밀번호를 위해 
	private String password;
	
	@Column(nullable=false, length=50)
	private String email;
	
	//@ColumnDefault("'user'")
	@Enumerated(EnumType.STRING) //DB는 RoleType이 없으므로 String type이라고 DB에 알려준다
	private RoleType role; //Enum을 이용한 type의 강제
	
	@CreationTimestamp //현재의 시간이 자동 입력
	private Timestamp createDate;
}
