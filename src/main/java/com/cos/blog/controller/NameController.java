package com.cos.blog.controller;



import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.cos.blog.model.KakaoProfile;
import com.cos.blog.model.Name;
import com.cos.blog.model.OAuthToken;
import com.cos.blog.service.NameService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Controller
public class NameController {
	
	@Value("${cos.key}")
	private String cosKey;
	
	@Autowired
	private NameService  nameService;
	
	@Autowired
	private AuthenticationManager authenticationManger;

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
	public  String kakaoCallback(String code) {
		
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
				e.printStackTrace();
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			System.out.println(oauthtoken.getAccess_token());
			
			RestTemplate rt2 = new RestTemplate();
			
			HttpHeaders headers2 = new HttpHeaders();
			headers2.add("Authorization", "Bearer "+oauthtoken.getAccess_token());
			headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
			
			
			//httpheader와 httpbody를 하나의 오브젝트에 담기
			HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest2 =
					new HttpEntity<>(headers2);
			
			//http 요청 - post - 그리고 response의 변수로 받음
			ResponseEntity<String> response2 = rt2.exchange(
					"https://kapi.kakao.com/v2/user/me",
						HttpMethod.POST,
						kakaoProfileRequest2,
						String.class	
					);
			
			ObjectMapper objectMapper2 = new ObjectMapper();
			KakaoProfile kakaoProfile = null;
			try {
				kakaoProfile = objectMapper2.readValue(response2.getBody(), KakaoProfile.class);
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			
			System.out.println("카카오 아이디(번호) : "+kakaoProfile.getId());
			System.out.println("카카오 이메일 : "+kakaoProfile.getKakao_account().getEmail());
			
			System.out.println("블로그 유저네임 : "+kakaoProfile.getKakao_account().getEmail()+"_"+kakaoProfile.getId());
			System.out.println("블로그 이메일 : "+kakaoProfile.getKakao_account().getEmail());
			//uuid -> 중복되지않는 어떤 특정 값을 만들어내는 알고리즘
			UUID garbagePassword = UUID.randomUUID();
			System.out.println("블로그서버 패스워드  : "+cosKey);
			
			
			//강제로 코딩으로 회원가입하기 월래는 폼만들어서 해야함
			Name kakaoUser = Name.builder()
					.username(kakaoProfile.getKakao_account().getEmail()+"_"+kakaoProfile.getId())
					.password(cosKey)
					.email(kakaoProfile.getKakao_account().getEmail())
					.oauth("kakao")
					.build();
			
			// 가입자 혹은 비가입자 체크 해서 처리
			Name originName = nameService.회원찾기(kakaoUser.getUsername());
			
			if(originName.getUsername() == null) {
				System.out.println("기존회원이 아닙니다 -------------");
				nameService.회원가입(kakaoUser);
			}
			
			System.out.println("자동로그인 진행");
			//로그인 처리
			Authentication authentication = authenticationManger.authenticate
					(new UsernamePasswordAuthenticationToken(kakaoUser.getUsername(), cosKey));
					SecurityContextHolder.getContext().setAuthentication(authentication);
			
		return "redirect:/";
	}
}
