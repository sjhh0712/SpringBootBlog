package com.cos.blog.test;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
	
	// user 전체를 반환
	@GetMapping("/dummy/users")
	public List<User> list(){
		return userRepository.findAll();
	}
	
	// 한페이지당 2건의 데이터를 리턴
	@GetMapping("/dummy/user")
	// List를 져와서 User객체 타입으로 반환하겠다.
	public List<User> pageList(@PageableDefault(size=2, sort="id", direction=Sort.Direction.DESC) Pageable pageable){
		// Page를 가져와서 User객체 타입으로 반환하겠다
		Page<User> pagingUser = userRepository.findAll(pageable);// -> 페이지 정보를 pagingUser에 저장.
		
		List<User> users = pagingUser.getContent(); // 페이지 정보에서 내용만 users에 저장
		return users;
	}
	
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
// 		람다식으로 사용하는 방법
//		User user = userRepository.findById(id).orElseThrow(()->{
//			return new IllegalArgumentException("존재하지 않는 유저입니다.");
//		});
		
		return user; // user 객체 = 자바 오브젝트
		// 웹브라우저한테 user 객체를 리턴 -> 웹브라우저는 자바 오브젝트를 알아들을수 없다. -> JSON을 사용하여 변환하여 브라우저에 return
		// 자바오브젝트 리턴시 스프링부트는 MessageConverter가 Jackson라이브러리 호출 -> user오브젝트를 JSON으로 변환하여 브라우저에게 전달
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
