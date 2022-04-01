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
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Board")
public class Board {

	@Id // 기본키
	@GeneratedValue(strategy = GenerationType.SEQUENCE) // 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다. 시퀀스,오토
	private int id; //시퀸스
	
	@Column(nullable = false, length = 100)
	private String title;
	
	@Lob // 대용량데이터
	private String content;
	
	private int count;
	
	@ManyToOne(fetch = FetchType.EAGER) // Many = Many, Name = one
	@JoinColumn(name="nameid")
	private Name name; //DB는 오브젝트를 저장할 수 없다. FK, 자바는 오브젝트를 저장할 수 있다.
	
	@OneToMany(mappedBy = "board", fetch = FetchType.EAGER) //난 fk가 아니다 db에 컬럼을 만들지 마세요.
	private List<Reply> reply;
	
	@CreationTimestamp
	private Timestamp createDate;
	
}
