package com.jsa.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsa.blog.model.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Integer>{

	
}
