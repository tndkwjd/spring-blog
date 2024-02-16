package com.jsa.blog.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.jsa.blog.model.Board;
import com.jsa.blog.model.Reply;
import com.jsa.blog.repository.BoardRepository;
import com.jsa.blog.repository.ReplyRepository;

@RestController
public class RelpyControllerTest {
	
	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired
	private ReplyRepository replyRepository;
	
	// http://localhost:8080/test/board/1
	@GetMapping("/test/board/{id}")
	public Board getBoard(@PathVariable int id) {
		return boardRepository.findById(id).get();
	}
	
	// http://localhost:8080/test/reply
	@GetMapping("/test/reply")
	public List<Reply> getReply() {
		return replyRepository.findAll();
	}
}
