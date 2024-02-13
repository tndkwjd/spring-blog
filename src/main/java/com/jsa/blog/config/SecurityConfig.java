package com.jsa.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.jsa.blog.config.auth.PrincipalDetailService;

@Configuration // IoC
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private PrincipalDetailService principalDetailService;
	 
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	
	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(principalDetailService).passwordEncoder(passwordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		  
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
			)
				.rememberMe() // 사용자 계정 저장
                .rememberMeParameter("remember") // default remember-me
                .tokenValiditySeconds(604800) // 7일 (default 14일)
                .alwaysRemember(false) // remember-me 기능 항상 실행
                .userDetailsService(principalDetailService); // 사용자 계정 조회
		
			// return http.build();
		}


	}

