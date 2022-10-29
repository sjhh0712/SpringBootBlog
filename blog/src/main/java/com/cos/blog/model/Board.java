package com.cos.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length = 100)
	private String title;
	
	@Lob // 대용량 데이터 저장 시 사용. (default는 varchar(255)까지 저장이 가능하지만 이미지등과 같은 데이터는 용량이 크기때문에 Lob를 사용한다.
	private String content; // 섬머노트 라이브러리 <html>태그가 섞여서 디자인이 됨
	
	@ColumnDefault("0")
	private int count; // 조회수
	
	// Many = Board, User = One --> 한명의 유저는 여러개의 게시글을 쓸 수 있다. user테이블과의 연관관계를 맺기위함 (1:N의 관계)
	@ManyToOne(fetch = FetchType.EAGER) // EAGER은 board테이블 셀렉트시 무조건 가져옴
	@JoinColumn(name="userId")
	private User userId; // DB는 오브젝트를 저장할 수 없다./ 자바는 오브젝트를 저장할 수 있다. --> DB에 FK로 Integer형태로 생성된다.
	
	 // 하나의 게시글은 여러개의 답변을 가질 수 있다. / mappedBy가 있으면 연관관계의 주인이 아님.(FK아님. 칼럼생성x)
	@OneToMany(mappedBy="board", fetch = FetchType.EAGER) // 
	private List<Reply> reply;
	
	@CreationTimestamp
	private Timestamp createDate;
}
