package com.cos.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.Board;
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
		String rawPassword = name.getPassword(); // 1234 원문
		String encPassword = encoder.encode(rawPassword); // 해쉬
		name.setPassword(encPassword);
		name.setRole(RoleType.USER);
		nameRepository.save(name);
	}
	@Transactional
	public void 회원수정(Name name) {
		//수정시 영속성 컨텍스트 user 오브젝트를 영속화시키고 영속화된 user 오브젝트를 수정
		//select 해서 user오브젝트를 db로 부터 가져와서 영속화
		//영속화된 오브젝트를 변경하면 자동으로 더티체킹
		Name persistance = nameRepository.findById(name.getId())
				.orElseThrow(()->{
					return new IllegalArgumentException("회원찾기실패");
				}); //영속화 완료
		String rawPassword = name.getPassword(); // 1234 원문
		String encPassword = encoder.encode(rawPassword); // 해쉬
		persistance.setPassword(encPassword);
		persistance.setEmail(name.getEmail());
		

		//회원수정 함수 종료 = 서비스 종료 = 트랜잭션 종료 = commit
	}
	

	
	

	// 전통적인방식
//	@Transactional(readOnly = true) select할때 트랜잭션 시작, 서비스 종료시에 트랜잭션 종료(정합성)
//	public Name 로그인(Name name) {
//		return nameRepository.findByUsernameAndPassword(name.getUsername(),name.getPassword());
//		
//	}

}
