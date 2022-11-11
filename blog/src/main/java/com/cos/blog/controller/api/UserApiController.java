package com.cos.blog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;

@RestController // data만 return할거임
public class UserApiController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/api/user")
	public ResponseDto<Integer> save(@RequestBody User user) { // user는 username,password,email 3개 밖에 없음 role은 강제로 넣어줘야됨
		System.out.println("api 호출");
		// db에 insert하고 아래에서 return이 됨
		user.setRole(RoleType.USER);
		int result = userService.JoinUser(user);
		return new ResponseDto<Integer>(HttpStatus.OK, result);
	}
}
