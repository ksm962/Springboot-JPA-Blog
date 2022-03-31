package com.cos.blog.api;

import javax.servlet.http.HttpSession;

import org.apache.catalina.connector.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.Name;
import com.cos.blog.model.RoleType;
import com.cos.blog.service.NameService;

@RestController
public class NameApiController {
	
	@Autowired
	private NameService nameService;

	@PostMapping("/auth/joinProc")
	public ResponseDto<Integer> save(@RequestBody Name name) {
		System.out.println("NameApiController : save 호출됨");
		//실제로 db에 insert를 하고 아래에서 return
		 nameService.회원가입(name);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); //성공했다는것이 200
		//자바오브젝트를 json으로 변환해서 리턴(jackson)
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
