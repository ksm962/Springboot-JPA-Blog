package com.cos.blog.service;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.Name;
import com.cos.blog.model.RoleType;
import com.cos.blog.repository.NameRepository;

//스프링이 컴포넌트 스캔을 통해서 bean에 등록을 해줌. loc를 해준다
@Service
public class NameService {

	@Autowired
	private NameRepository nameRepository;
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Transactional
	public void 회원가입(Name name) {
		String rawPassword = name.getPassword(); //1234 원문
		String encPassword = encoder.encode(rawPassword); // 해쉬
		name.setPassword(encPassword);
		name.setRole(RoleType.USER);
		nameRepository.save(name);
	}
	
	
	
	
	
	
	
	//전통적인방식
//	@Transactional(readOnly = true)//select할때 트랜잭션 시작, 서비스 종료시에 트랜잭션 종료(정합성)
//	public Name 로그인(Name name) {
//		return nameRepository.findByUsernameAndPassword(name.getUsername(),name.getPassword());
//		
//	}
	
}
