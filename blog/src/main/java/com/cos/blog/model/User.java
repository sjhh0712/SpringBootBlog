package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder // 빌더 패턴
@Entity // User 클래스가 클래스 프로퍼티를 읽어서 자동으로 Mysql에 테이블이 생성된다.
public class User {
	
	@Id // primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에서 연결된 db의 넘버링(autoincrement) 전략을 따라간다.
	private int id; // autoincrement
	
	@Column(nullable = false, length = 30)
	private String username; // 아이디
	
	@Column(nullable = false, length = 100) // length를 100이나 주는 이유는 암호화때문
	private String password;
	
	@Column(nullable = false, length = 50)
	private String email;
	
	@ColumnDefault(" 'user' ")
	private String role; // Enum을 쓰는게 좋다. // admin, user, manager 등등 같은 권한 타입
	
	@CreationTimestamp // 시간이 자동으로 입력되는 어노테이션
	private Timestamp createDate;
}
