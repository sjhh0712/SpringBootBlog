package com.cos.blog.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


// -- lombok을 이용한 Getter,Setter,생성자 생성방법
//@Getter
//@Setter
@Data  // Getter, Setter 한꺼번에 생성
@AllArgsConstructor // 생성자 생성
@NoArgsConstructor // 빈 생성자 생성
//@RequiredArgsConstructor  --> final들의 생성자를 만들어줌
public class Member {
	private int id;
	private String username;
	private String password;
	private String email;
}

