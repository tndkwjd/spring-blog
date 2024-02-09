package com.jsa.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

	// http://localhost:8080/blog/
	@GetMapping({"","/"})
	public String index() {
		return "index";
	}
}
