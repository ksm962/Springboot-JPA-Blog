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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

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
@SequenceGenerator(
        name="Reply_SEQ_GEN", //시퀀스 제너레이터 이름
        sequenceName="Reply_SEQ", //시퀀스 이름
        initialValue= 1, //시작값
        allocationSize= 1 //메모리를 통해 할당할 범위 사이즈
        )
public class Reply {

	@Id // 기본키
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
									generator = "Reply_SEQ_GEN") // 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다. 시퀀스,오토
	private int id; //시퀸스
	
	@Column(nullable = false, length = 200)
	private String content;
	
	@ManyToOne
	@JoinColumn(name = "boardid")
	private Board board;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="nameid")
	private Name name;
	
	
	@OneToMany(mappedBy = "board", fetch = FetchType.EAGER) // mappedBy 연관관계의 주인이 아니다(난 fk가 아님)db에 칼럼을 만들지 마세요
	private List<Reply> reply; 
	
	@CreationTimestamp
	private Timestamp createDate;
	
}
