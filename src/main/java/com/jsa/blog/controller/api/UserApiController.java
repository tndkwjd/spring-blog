package com.jsa.blog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jsa.blog.dto.ResponseDTO;
import com.jsa.blog.model.User;
import com.jsa.blog.service.UserSerivce;

@RestController
public class UserApiController {

	@Autowired
	private UserSerivce userSerivce;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/auth/joinProc")
	public ResponseDTO<Integer> save(@RequestBody User user) {
		userSerivce.join(user);
		return new ResponseDTO<Integer>(HttpStatus.OK.value(), 1); // Java Object를 JSON으로 변환해서 리턴 (Jackson)
	}

	@PutMapping("/user")
	public ResponseDTO<Integer> update(@RequestBody User user) {
		userSerivce.update(user);
		// 트랜잭션 종료 되기 때문에 DB 값은 변경되지만 session 값은 변경 되지 않은 상태

		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		return new ResponseDTO<Integer>(HttpStatus.OK.value(), 1);
	}

}
