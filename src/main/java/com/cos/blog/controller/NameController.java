package com.cos.blog.controller;



import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.cos.blog.model.OAuthToken;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


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
	@GetMapping({"/auth/kakao/callback"})
	public @ResponseBody String kakaoCallback(String code) {
		
		//post 방식으로 key-value 데이터요청(카카오쪽으로)
		//Retrofit2
		//okhttp
		//RestTemplate
		
	
		RestTemplate rt = new RestTemplate();
		//httpheader 오브젝트 생성
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		
		//httpbody 오브젝트 생성
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", "dd60cfbc185c0eabbd4acf3cb42c580c");
		params.add("redirect_uri", "http://localhost:8000/auth/kakao/callback");
		params.add("code", code);
		
		//httpheader와 httpbody를 하나의 오브젝트에 담기
		HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest =
				new HttpEntity<>(params, headers);
		
		//http 요청 - post - 그리고 response의 변수로 받음
		ResponseEntity<String> response = rt.exchange(
				"https://kauth.kakao.com/oauth/token",
					HttpMethod.POST,
					kakaoTokenRequest,
					String.class	
				);
			ObjectMapper objectMapper = new ObjectMapper();
			OAuthToken oauthtoken = null;
			try {
				oauthtoken = objectMapper.readValue(response.getBody(), OAuthToken.class);
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(oauthtoken.getAccess_token());
		return response.getBody();
	}
}
