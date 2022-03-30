package com.cos.blog.service;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.Name;
import com.cos.blog.repository.NameRepository;

//스프링이 컴포넌트 스캔을 통해서 bean에 등록을 해줌. loc를 해준다
@Service
public class NameService {

	@Autowired
	private NameRepository nameRepository;
	
	@Transactional
	public int 회원가입(Name name) {
		
		try {
			nameRepository.save(name);
			return 1;
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("NameServic : 회원가입() : " + e.getMessage());
		}
		return -1;
	}
	@Transactional(readOnly = true)//select할때 트랜잭션 시작, 서비스 종료시에 트랜잭션 종료(정합성)
	public Name 로그인(Name name) {
		return nameRepository.findByUsernameAndPassword(name.getUsername(),name.getPassword());
		
	}
	
}
