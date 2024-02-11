package com.jsa.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsa.blog.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	Optional<User> findByUsername(String username);
	
	// JPA Query Methods
	// SELECT * FROM user WHERE userName = ? AND password = ?;
	// User findByUserNameAndPassword(String userName, String password);
	
}
