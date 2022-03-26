package com.cos.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HttpControllerTest {

	public static String TAG = "HttpControllerTest:";
	
	//http://localhost:8081/http/lombok
	@GetMapping("/http/lombok")
	public String lombokTest() {
		Member m = new Member("asd", "ss23", "1234", "12333");
		//빌더는 자기가 원하는 값을 순서 상관없이 넣을수 있다.
		Member m2 = Member.builder().email("asd@naver.com").id("asd221").password("asdasd12") .build();
		System.out.println(TAG + "getter : " +m.getId());
		m.setId("휘유유");
		System.out.println(TAG + "setter : " +m.getId());
		return "lombok 완료";
	}
	
	
	//인터넷 브라우저 요청은 get 요청만 가능
	//http://localhost:8000/http/get
	@GetMapping("/http/get")
	public String getTest() {
		return "get 요청";
	}
	//http://localhost:8081/http/post
	@PostMapping("/http/post")
	public String postTest() {
		return "post 요청";
	}
	//http://localhost:8081/http/put
	@PutMapping("/http/put")
	public String putTest() {
		return "put 요청";
	}
	//http://localhost:8081/http/delete
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "delete 요청";
	}
	
}
