package com.jsa.blog.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.jsa.blog.model.Board;

public interface BoardRepository extends JpaRepository<Board, Integer>{
	
	Page<Board> findByTitleContaining(String keyword, Pageable pageable);
	Page<Board> findByContentContaining(String keyword, Pageable pageable);
	Page<Board> findByTitleContainingOrContentContaining(String keyword, String searchType, Pageable pageable);
	
}
