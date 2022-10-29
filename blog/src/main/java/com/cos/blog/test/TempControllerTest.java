package com.cos.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // spring의 Controller는 기본적으로 file을 return함
public class TempControllerTest {
	
	//http:localhost:8000/blog/temp/home
	@GetMapping("temp/home")
	public String tempHome() {
		System.out.println("tempHome()");
		// 파일 리턴 기본경로 : src/main/resource/static
		// 리턴명을 /home.html 이라고 해야됨
		return "/home.html";
	}
	
	@GetMapping("temp/jsp")
	public String tempJsp() {
		return "test";
	}
}