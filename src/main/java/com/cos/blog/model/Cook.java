package com.cos.blog.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@DynamicUpdate
public class Cook {
	@Id	//Primary Key
	@GeneratedValue(strategy = GenerationType.IDENTITY)		// 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
	private int id; //시퀀스, auto-increment
	
	@Column(nullable = false, length = 100)
	private String cook_name;
	
	private int cook_kind; //요리:1 ,베이킹:2
	
	@ManyToOne(fetch = FetchType.EAGER)		//Cook정보를 select하면 User정보를 가져오겠다. 한건밖에 없으니까
	@JoinColumn(name="userId")
	private User user;	//DB는 오브젝트를 저장할 수 없다.FK, 자바는 오브젝트를 저장 할 수 있다.
	
	@CreationTimestamp
	private Timestamp createDate;
	
	//@OneToMany(mappedBy = "cook", cascade = CascadeType.ALL)
    //private List<CookSub> cooksubList = new ArrayList<>();
	
	@OneToMany	(mappedBy = "cook", fetch = FetchType.EAGER)	//mappeBy 연관관계의 주인이 아니다(난 FK가 아니에요) DB에 컬럼을 만들지 마세요., 값을 얻기위해 필요한거다.
	private List<CookSub> cooksubList = new ArrayList<>();
	
	public void addCookSub(CookSub cooksub) {
		cooksubList.add(cooksub);
		cooksub.setCook(this);
	}
}
