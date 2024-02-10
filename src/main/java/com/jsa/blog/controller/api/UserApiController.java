package com.jsa.blog.controller.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jsa.blog.dto.ResponseDTO;
import com.jsa.blog.model.RoleType;
import com.jsa.blog.model.User;
import com.jsa.blog.service.UserSerivce;

@RestController
public class UserApiController {

	@Autowired
	private UserSerivce userSerivce;
	
	@Autowired
	private HttpSession session;
	
	@PostMapping("/api/user")
	public ResponseDTO<Integer> save(@RequestBody User user) {
		System.out.println("UserApiController : save 호출" );
		user.setRole(RoleType.USER);
		userSerivce.join(user);
		return new ResponseDTO<Integer>(HttpStatus.OK.value(), 1);
	}
	
	@PostMapping("/api/user/login")
	public ResponseDTO<Integer> login(@RequestBody User user){
		System.out.println("UserApiController : login 호출" );
		user.setRole(RoleType.USER);
		User principal = userSerivce.login(user);
		
		if(principal != null) {
			session.setAttribute("principal", principal);
		}
		return new ResponseDTO<Integer>(HttpStatus.OK.value(), 1);
	}
}
