package com.jsa.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.jsa.blog.config.auth.PrincipalDetailService;

@Configuration // IoC
public class SecurityConfig {
	
	@Autowired
	private PrincipalDetailService principalDetailService;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Bean
	BCryptPasswordEncoder encode() {
		return new BCryptPasswordEncoder();
	}

	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(principalDetailService).passwordEncoder(encode());
	}
	
	@Bean
	SecurityFilterChain configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable() // csrf 토큰 비활성화
			.authorizeRequests() // 인증 주소 설정
				.antMatchers("/","/auth/**","/js/**","/css/**","/image/**","/dummy/**")
				.permitAll()
				.anyRequest()
				.authenticated()
				.and()
			// 로그인 처리 프로세스 설정
				.formLogin(f -> f.loginPage("/auth/loginForm")
				.loginProcessingUrl("/auth/loginProc")
				.defaultSuccessUrl("/")
			);

			return http.build();
		}
	}

