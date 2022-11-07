package com.cos.blog.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice // 어디에서든 Exception이 발생하면 이곳으로 올 수 있게끔함
@RestController // Json형식으로 객체를 반환하기위해 @RestController를 사용
public class GlobalExceptionHandler {
	
	@ExceptionHandler(value=Exception.class) // 어떤 Exception만 받을지 결정.(Exception은 모든 Exception의 부모이기때문에 모든 Exception을 처리가능)
	public String handleArgumentException(IllegalArgumentException e) {
		return "<h1>" + e.getMessage() + "</h1>";
	}
}
