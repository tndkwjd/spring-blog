package com.jsa.blog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jsa.blog.dto.ResponseDTO;
import com.jsa.blog.model.User;
import com.jsa.blog.service.UserSerivce;

@RestController
public class UserApiController {

	@Autowired
	private UserSerivce userSerivce;

	@PostMapping("/auth/joinProc")
	public ResponseDTO<Integer> save(@RequestBody User user) {
		System.out.println("UserApiController : save 호출");
		userSerivce.join(user);
		return new ResponseDTO<Integer>(HttpStatus.OK.value(), 1); // Java Object를 JSON으로 변환해서 리턴 (Jackson)
	}

	
}
