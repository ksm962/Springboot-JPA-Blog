package com.cos.blog.controller;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.cos.blog.config.auth.PrincipalDetail;


@Controller
public class BoardController {

//	컨트롤러에서 세션넣기
//	@GetMapping({"","/"})
//	public String index(@AuthenticationPrincipal  PrincipalDetail principal) {
//		// /web-inf/views/index.jsp
//		System.out.println("로그인 사용자 아이디 : " + principal.getUsername());
//		return "index";
//	}
	
	
	@GetMapping({"","/"})
	public String index() { //컨트롤러에서 세션찾기
		// /web-inf/views/index.jsp
		return "index";
	}
	@GetMapping({"/board/saveForm"})
	public String saveForm() { 
		return "board/saveForm";
	}
	
	
}
