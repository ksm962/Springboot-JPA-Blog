package com.cos.blog.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cos.blog.model.Name;

import lombok.Data;
import lombok.Getter;

// 스프링 시큐리티가 로그인 요청을 가로채서 로그인을 진행하고
// 완료가 되면 userdetails 타입의 오브젝트를 스프링 시큐리티의 고유한 세션저장소에 저장을 해준다.
@Getter
public class PrincipalDetail implements UserDetails{
	private Name name; //콤포지션
	
	public PrincipalDetail(Name name) {
		// TODO Auto-generated constructor stub
		this.name = name;
	}


	@Override
	public String getPassword() {
		return name.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return name.getUsername();
	}

	//계정이 만료되지 않았는지 리턴한다.(true:만료안됨)
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	//계정이 잠겨있지 않았는지 리턴한다. (true: 잠기지 않음)
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	//비밀번호가 완료되지 않았는지 리턴한다 (true : 만료안됨)
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	//계정이 활성화인지 리턴한다(true : 활성화)
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	// 계정이 갖고있는 권한 목록을 리턴한다.(권한이 여러개 있을 수 있어서 루프를 돌려야함)
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		Collection<GrantedAuthority> collectors = new ArrayList<>(); //list 올라가다보면 collection
//		collectors.add(new GrantedAuthority() {
//			
//			@Override
//			public String getAuthority() {
//				return "ROLE_"+name.getRole(); // "ROLE_"는 규칙, 자바는 객체안에 메소스실행이 안됨
//			}														   // GrantedAuthority안에 메소드가 1개밖에 없기떄문에
//		});														   // 람다식으로 포현
		
		collectors.add(()->{return "Role_"+name.getRole();});
		
		return collectors;
	}
	
	
	
}
