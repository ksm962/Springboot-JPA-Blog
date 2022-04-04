package com.cos.blog.api;

import javax.servlet.http.HttpSession;

import org.apache.catalina.connector.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.Name;
import com.cos.blog.model.RoleType;
import com.cos.blog.service.NameService;

@RestController
public class NameApiController {
	
	@Autowired
	private NameService nameService;
	@Autowired
	private AuthenticationManager authenticationManger;

	@PostMapping("/auth/joinProc")
	public ResponseDto<Integer> save(@RequestBody Name name) {
		System.out.println("NameApiController : save 호출됨");
		//실제로 db에 insert를 하고 아래에서 return
		 nameService.회원가입(name);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); //성공했다는것이 200
		//자바오브젝트를 json으로 변환해서 리턴(jackson)
	}
	@PutMapping("/name/update")
	public ResponseDto<Integer> update(@RequestBody Name name) {
		 nameService.회원수정(name);
		 //여기서는 트랜잭션이 종료되기 때문에 db값이 변경이 됌
		 //하지만 세션값은 변경되지 않은 상태이기떄문에 우리가 직접 세션값을 변경
			//세션등록
			Authentication authentication = authenticationManger.authenticate
			(new UsernamePasswordAuthenticationToken(name.getUsername(), name.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authentication);
		 
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); //성공했다는것이 200
	}
	
	
//	전통적인 방식
//	@PostMapping("/api/name/login")
//	public ResponseDto<Integer>login(@RequestBody Name name, HttpSession session){
//		System.out.println("NameApiController : login 호출됨");
//		Name principal = nameService.로그인(name); //principal (접근추체)
//		
//		if(principal != null) {
//			session.setAttribute("principal", principal);
//		} else {
//			
//		}
//		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); 
//		
//		
//	}
}
