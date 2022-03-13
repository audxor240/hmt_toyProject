package com.cos.blog.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

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

@Data
@AllArgsConstructor
@Builder
@Entity
@DynamicUpdate
public class CookSub {
	@Id	//Primary Key
	@GeneratedValue(strategy = GenerationType.IDENTITY)		// 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
	private int id; //시퀀스, auto-increment
	
	private int	gram;		//양
	
	@Column(length = 100)
	private String material;	//재료
	
	@ManyToOne(fetch = FetchType.EAGER)		//Cook정보를 select하면 CookSub정보를 가져오겠다. 한건밖에 없으니까
	@JoinColumn(name="cookId")
	private Cook cook;	//DB는 오브젝트를 저장할 수 없다.FK, 자바는 오브젝트를 저장 할 수 있다.

	@CreationTimestamp
	private Timestamp createDate;
	
	//@OneToMany(mappedBy = "cookSub")
	//private Set<Cook> cooks = new HashSet<>();
	
	public CookSub() {
		
	}
}
