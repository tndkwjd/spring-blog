package com.jsa.blog.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jsa.blog.model.User;
import com.jsa.blog.repository.UserRepository;

@Service
public class UserSerivce {

	@Autowired
	private UserRepository userRepository;

	@Transactional
	public void join(User user) {
		userRepository.save(user);
	}

	@Transactional(readOnly = true) // SELECT 할 때 트랜잭션 시작, 서비스 종료 시 트랜잭션 종료(정합성)
	public User login(User user) {
		return userRepository.findByUserNameAndPassword(user.getUserName(), user.getPassword());
	}
}
