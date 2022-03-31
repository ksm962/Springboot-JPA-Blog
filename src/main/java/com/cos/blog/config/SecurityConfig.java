package com.cos.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//빈 등록 : 스프링 컨테이너에서 객체를 관리 할 수 있게 하는것
@Configuration // 빈등록(loc관리)
@EnableWebSecurity //시큐리티 필터가 등록이 된다.
@EnableGlobalMethodSecurity(prePostEnabled = true) // 특정 주소로 접근을 하면 권한 및 인증을 미리 체크하겠다는 뜻
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean // ioc
	public BCryptPasswordEncoder encodePWD() {
		String encPassword = new BCryptPasswordEncoder().encode("1234");
		return new BCryptPasswordEncoder();
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http
			.csrf().disable() //csrf 토큰 비활성화(테스트시 걸어두기)
			.authorizeRequests()
				.antMatchers("/","/auth/**","/js/**","/css/**","/image/**")
				.permitAll()
				.anyRequest()
				.authenticated()
			.and()
				.formLogin()
				.loginPage("/auth/loginForm");
				
	}
}
