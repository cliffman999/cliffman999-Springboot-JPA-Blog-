package com.cos.blog.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.dto.ResponseDto;

@ControllerAdvice //익셉션이 발생했을떄 이 클래스로 들어올수 있게 함
@RestController
public class GlobalExceptionHandler {
		
	@ExceptionHandler(value=Exception.class) //value값에 지정된 특정 익셉션에 대한 에러를 이 함수로 전달
	public ResponseDto<String> handleArgumentException(Exception e) {
		return new ResponseDto<String>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
	}
}
  