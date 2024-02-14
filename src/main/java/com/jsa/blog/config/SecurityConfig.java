package com.jsa.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.jsa.blog.config.auth.PrincipalDetailService;

@Configuration // IoC
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private PrincipalDetailService principalDetailService;
	 
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	SecurityFilterChain filterChaine(HttpSecurity http) throws Exception {
		  
		http
			.csrf().disable() // csrf 토큰 비활성화
			.authorizeRequests() // 인증 주소 설정
				.antMatchers("/","/auth/**","/js/**","/css/**","/image/**","/dummy/**")
				.permitAll()
				.anyRequest()
				.authenticated()
				.and()
				.formLogin(f -> f.loginPage("/auth/loginForm")
						.loginProcessingUrl("/auth/loginProc")
						.defaultSuccessUrl("/")
				);

				return http.build();
		}


	}

