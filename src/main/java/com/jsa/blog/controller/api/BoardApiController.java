package com.jsa.blog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

	@DeleteMapping("/api/board/{id}")
	public ResponseDTO<Integer> deleteById(@PathVariable int id){
		boardSerivce.delete(id);
		return new ResponseDTO<Integer>(HttpStatus.OK.value(), 1);
	}
	
	@PutMapping("/api/board/{id}")
	public ResponseDTO<Integer> update(@PathVariable int id, @RequestBody Board board){
		boardSerivce.update(id, board);
		return new ResponseDTO<Integer>(HttpStatus.OK.value(), 1);
	}
}
