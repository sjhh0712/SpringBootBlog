package com.cos.blog.controller.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.User;

@RestController // data만 return할거임
public class UserApiController {
	
	@PostMapping("/api/user")
	public ResponseDto<Integer> save(@RequestBody User user) {
		System.out.println("api 호출");
		// db에 inser하고 아래에서 return이 됨
		
		return new ResponseDto<Integer>(HttpStatus.OK, 1);
	}
}
