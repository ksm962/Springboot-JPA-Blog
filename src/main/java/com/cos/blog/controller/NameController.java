package com.cos.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NameController {

	@GetMapping("/name/joinForm")
	public String joinForm(){
		
		return "name/joinForm";
	}
	
	@GetMapping("/name/loginForm")
	public String loginForm(){
		
		return "name/loginForm";
	}
}
