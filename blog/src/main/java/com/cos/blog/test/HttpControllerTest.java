package com.cos.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

// 사용자 요청 -> 응답(HTML) : @Controller

// 사용자 요청 -> 응답(Data)
@RestController
public class HttpControllerTest {
	
	private static final String TAG = "HttpControllerTest :";
	
	@GetMapping("/http/lombok")
	public String lombokTest() {
		Member m = new Member(1, "ssar", "1234", "email");
		System.out.println(TAG+"getter : " + m.getId());
		m.setId(5000);
		System.out.println(TAG+"setter : " + m.getId());
		return "lombok test 완료";
	}
	
	// http://localhost:8000/http/get (select)
	@GetMapping("/http/get")
	public String getTest(Member m) { // id=1&username=ssar&password=1234&email=ssar@naver.com 를 m에 넣어주는 역할을 스프링이함
		return "get 요청:" + m.getId() + ',' + m.getUsername() + ',' + m.getPassword() + ',' + m.getEmail();
	}
	
	//인터넷 브라우저 요청은 무조건 get요청밖에 안됨
	// http://localhost:8000/http/post (insert)
	@PostMapping("/http/post")
	public String postTest(@RequestBody Member m) {
		return "post 요청:" + m.getId() + ',' + m.getUsername() + ',' + m.getPassword() + ',' + m.getEmail();
	}
	
	// http://localhost:8000/http/put (update)
	@PutMapping("/http/put")
	public String putTest(@RequestBody Member m) {
		return "put 요청:" + m.getId() + ',' + m.getUsername() + ',' + m.getPassword() + ',' + m.getEmail();
	}
	
	// http://localhost:8000/http/delete (delete)
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "delete 요청";
	}
}
