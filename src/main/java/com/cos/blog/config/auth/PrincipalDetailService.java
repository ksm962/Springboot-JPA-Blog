package com.cos.blog.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cos.blog.model.Name;
import com.cos.blog.repository.NameRepository;



@Service //빈 등록
public class PrincipalDetailService implements UserDetailsService{

	@Autowired
	private NameRepository nameRepository;
	
	// 스프링이 로그인 요쳥을 가로챌떄, username, password 변수 2개를 가로채는데
	// password 부분처리는 알아서함.
	// username이 db에 있는지만 확인해주면 됨
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		
		Name principal = nameRepository.findByUsername(username)
		.orElseThrow(()->{
					System.out.println(username);
					return new UsernameNotFoundException("해당 사용자를 찾을 수 업습니다.:" +username );
				});
		return new PrincipalDetail(principal); // 시큐리티 세션에 유저 정보가 저장이 됨.
		// 만약 디테일에 유저정보를 안넣는다면 id : user 비번 : 콘솔창
	}
}
