package com.jsa.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jsa.blog.model.Board;
import com.jsa.blog.service.BoardSerivce;

@Controller
public class BoardController {

	@Autowired
	private BoardSerivce boardService;

	@GetMapping({ "", "/" })
	public String index(Model model, @RequestParam(required = false, defaultValue = "") String keyword,
			@RequestParam(required = false, defaultValue = "") String searchType,
			@PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
		Page<Board> boards = boardService.list(keyword, searchType, pageable);

		int startPage = Math.max(1, Math.min(boards.getPageable().getPageNumber() - 2, boards.getTotalPages() - 4));
		int endPage = Math.min(boards.getPageable().getPageNumber() + 2, boards.getTotalPages());

		model.addAttribute("boards", boards);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);

		return "index";
	}

	@GetMapping("/board/saveForm")
	public String saveForm() {
		return "board/saveForm";
	}
}
