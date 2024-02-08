package com.jsa.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HttpControllerTest {

	// http://localhost:8080/http/get (select)
	@GetMapping("/http/get")
	public String getTest(Member member) {
		return "get 요청"+member.getId()+", "+member.getUsername()+", " +member.getPassword()+ ", " + member.getEmail();
	}
	
	// http://localhost:8080/http/post (insert)
	@PostMapping("/http/post")
	public String postTest(@RequestBody Member member) {
		return "post 요청"+member.getId()+", "+member.getUsername()+", " +member.getPassword()+ ", " + member.getEmail();
	}
	
	// http://localhost:8080/http/put (update)
	@PutMapping("/http/put")
	public String putTest(@RequestBody Member member) {
		return "put 요청"+member.getId()+", "+member.getPassword();
	}
	
	// http://localhost:8080/http/delete (delete)
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "delete 요청";
	}
}
