package com.cos.blog.test;

import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@RestController
public class DummyControllerTest {
	
	@Autowired // 의존성 주입  ->  패키지 스캔 시 현재 컨트롤러가 메모리에 올라가는데 UserRepository는 null상태. 이걸 Autowired가 bean을 주입하여 프로퍼티 사용가능
	private UserRepository userRepository;
	
	// {id}주소로 파라미터를 전달 받을 수 있음
	// http://localhost:8000/blog/dummy/user/3
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		// db에서 값을 못찾는경우 user가 null이 됨. -> return null이 됨.
		// Optional(.)로 User객체를 감싸서 가져오면 값이 null인지 아닌지 판단하여 return.
		// IllegalArgumentException -> 잘못된 정보가 들어온 경우 처리
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			public IllegalArgumentException get() {
				// TODO Auto-generated method stub
				return new IllegalArgumentException("존재하지 않는 유저입니다. id : " + id);
			}
		});
		return user;
	}
	
	// http://localhost:8000/blog/dummy/join (요청)
	// http의 body에 username,password,,email 데이터를 가지고 요청
	@PostMapping("/dummy/join")
	public String join(User user /*String username, String password, String email*/) { // object로 받아올수도 있음		
		System.out.println("id : " +user.getId());
		System.out.println("username : " +user.getUsername());
		System.out.println("password : " +user.getPassword());
		System.out.println("email : " +user.getEmail());
		System.out.println("role : " +user.getRole());
		
		user.setRole(RoleType.USER);
		userRepository.save(user);
		return "회원가입이 완료되었습니다.";
	}
}
