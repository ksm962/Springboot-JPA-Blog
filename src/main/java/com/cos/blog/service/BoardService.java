package com.cos.blog.service;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.Board;
import com.cos.blog.model.Name;
import com.cos.blog.model.RoleType;
import com.cos.blog.repository.BoardRepository;
import com.cos.blog.repository.NameRepository;

//스프링이 컴포넌트 스캔을 통해서 bean에 등록을 해줌. loc를 해준다
@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;

	@Transactional
	public void 글쓰기(Board board, Name name) { //title, content
		board.setCount(0);
		board.setName(name);
		boardRepository.save(board);

	}
	
	
	
	
	
	
	
	//전통적인방식
//	@Transactional(readOnly = true)//select할때 트랜잭션 시작, 서비스 종료시에 트랜잭션 종료(정합성)
//	public Name 로그인(Name name) {
//		return nameRepository.findByUsernameAndPassword(name.getUsername(),name.getPassword());
//		
//	}
	
}
