package com.cos.blog.controller;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class NameController {

	//인증이 안된 사용자들이 출입할 수 있는 경로(/auth/**)
	//그낭 주소가 / 이면 index.jsp 허용
	//static이하에 있는 /js/**,/css/**,/image/**
	
	@GetMapping("/auth/joinForm")
	public String joinForm(){
		return "name/joinForm";
	}
	
	@GetMapping("/auth/loginForm")
	public String loginForm(){
		return "name/loginForm";
	}
	@GetMapping({"/name/updateForm"})
	public String updateForm() {
		return "name/updateForm";
	}
	
}
