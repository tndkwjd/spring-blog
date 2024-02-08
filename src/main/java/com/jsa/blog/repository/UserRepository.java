package com.jsa.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsa.blog.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	
}
