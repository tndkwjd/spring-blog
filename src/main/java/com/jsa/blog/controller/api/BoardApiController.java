package com.jsa.blog.controller.api;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jsa.blog.config.auth.PrincipalDetail;
import com.jsa.blog.dto.ResponseDTO;
import com.jsa.blog.model.Board;
import com.jsa.blog.service.BoardSerivce;

@RestController
public class BoardApiController {
	
	@Autowired
	private BoardSerivce boardSerivce;
	
	@PostMapping("/api/board")
	public ResponseDTO<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principal) {
		boardSerivce.write(board, principal.getUser());
		return new ResponseDTO<Integer>(HttpStatus.OK.value(), 1);
	}

	
}
