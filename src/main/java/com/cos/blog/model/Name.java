package com.cos.blog.model;

import java.sql.Timestamp;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor

//ORM - > Java(다른언어) object -> 테이블로 매핑해주는 기술
@Entity // User 클래스가 오라클에 테이블이 생성이 된다.
@Table(name = "Name")
//@DynamicInsert insert 시에 null인 필드는 제외
@SequenceGenerator(
        name="Name_SEQ_GEN", //시퀀스 제너레이터 이름
        sequenceName="Name_SEQ", //시퀀스 이름
        initialValue=1, //시작값
        allocationSize=1 //메모리를 통해 할당할 범위 사이즈
        )
public class Name {

	@Id // 기본키
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
									generator = "Name_SEQ_GEN") // 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다. 시퀀스,오토
	private int id; //시퀸스
	
	@Column(nullable = false, length = 100, unique = true)
	private String username;
	
	@Column(nullable = false, length = 100)
	private String password;
	
	@Column(nullable = false, length = 50)
	private String email;
	
	private String oauth; //kakao, google
	//@ColumnDefault("user")
	
	//DB는 RoleType이라는게 없어서 알려줘야된다.
	@Enumerated(EnumType.STRING)
	private RoleType role; // Enum(도메인범위적용가능)을 쓰는게 좋다. // USER,ADMIN만 사용가능

	@CreationTimestamp
	private Timestamp createDate;
		
	
}
