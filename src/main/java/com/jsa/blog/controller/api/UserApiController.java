package com.jsa.blog.controller.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jsa.blog.dto.ResponseDTO;
import com.jsa.blog.model.User;

@RestController
public class UserApiController {

	@PostMapping("/api/user")
	public ResponseDTO<Integer> save(@RequestBody User user) {
		System.out.println("UserApiController : save 호출" );
		return new ResponseDTO<Integer>(HttpStatus.OK,1);
	}
}
