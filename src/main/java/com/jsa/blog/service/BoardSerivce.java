package com.jsa.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jsa.blog.model.Board;
import com.jsa.blog.model.User;
import com.jsa.blog.repository.BoardRepository;

@Service
public class BoardSerivce {

	@Autowired
	private BoardRepository boardRepository;
	
	@Transactional
	public void write(Board board, User user) {
		board.setCount(0);
		board.setUser(user);
		boardRepository.save(board);
	}
	
	@Transactional(readOnly = true)
	public Page<Board> list(String keyword, String searchType, Pageable pageable){
		
		if ("title".equals(searchType)) {
            return boardRepository.findByTitleContaining(keyword, pageable);
        } else if ("content".equals(searchType)) {
            return boardRepository.findByContentContaining(keyword, pageable);
        } else {
            return boardRepository.findByTitleContainingOrContentContaining(keyword, keyword, pageable);
        }
		
	}
	
	@Transactional(readOnly = true)
	public Board listDetail(int id) {
		return boardRepository.findById(id)
				.orElseThrow(() -> { 
					return new IllegalArgumentException("글 상세보기 실패: 아이디를 찾을 수 없습니다.");
		});
	
	}
	
	@Transactional
	public void update(int id, Board requestBoard) {
		Board board = boardRepository.findById(id)
			.orElseThrow(() -> { 
				return new IllegalArgumentException("글 찾기 실패: 아이디를 찾을 수 없습니다.");
		});
		
		board.setTitle(requestBoard.getTitle());
		board.setContent(requestBoard.getContent());
	}
	
	@Transactional
	public void delete(int id) {
		boardRepository.deleteById(id);
	}

}
