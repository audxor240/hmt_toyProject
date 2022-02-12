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
@Entity	//Board 테이블을 자동으로 생성
public class Board {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)		//auto_increment
	private int id;
	
	@Column(nullable = false, length = 100)
	private String title;
	
	@Lob	//대용량 데이터
	private String content;	//섬머노트 라이;브러리 <html>태그가 섞여서 디자인이 됨.
	
	private int count; //조회수
	
	//Many = Board, User = One
	//EAGER : 무조건 가져온다
	//LAZY : 필요할때 가져온다( ex) 게시글 상세보기에서 최초 답글이 보이지 않고 펼치기 버튼으로 답글을 조회할때)
	@ManyToOne(fetch = FetchType.EAGER)		//Board정보를 select하면 User정보를 가져오겠다. 한건밖에 없으니까
	@JoinColumn(name="userId")
	private User user;	//DB는 오브젝트를 저장할 수 없다.FK, 자바는 오브젝트를 저장 할 수 있다.
	
	// 하나의 게시글은 여러개의 답변을 가질수 있다.
	
	@OneToMany	(mappedBy = "board", fetch = FetchType.EAGER)	//mappeBy 연관관계의 주인이 아니다(난 FK가 아니에요) DB에 컬럼을 만들지 마세요., 값을 얻기위해 필요한거다.
	private List<Reply> reply;	//reply의 여러 정보를 가져오기위해 List를 사용한다.
	
	@CreationTimestamp
	private Timestamp createDate;
	
}
