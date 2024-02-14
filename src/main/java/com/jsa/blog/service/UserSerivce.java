package com.jsa.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jsa.blog.model.RoleType;
import com.jsa.blog.model.User;
import com.jsa.blog.repository.UserRepository;

@Service
public class UserSerivce {
	 
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Transactional(readOnly=true)
	public User find(String username) {
		User user = userRepository.findByUsername(username).orElseGet(()->{
			return new User();
		});
		return user;
	}
	
	@Transactional
	public void join(User user) {
		String rawPassword = user.getPassword();
		String encPassword = encoder.encode(rawPassword); 
		user.setPassword(encPassword);
		user.setRole(RoleType.USER);
		userRepository.save(user);
	}
	
	@Transactional // 전체가 성공하면 commit, 실패하면 rollback
	public void update(User user) {
		User persistance = userRepository.findById(user.getId()).orElseThrow(()->{
			return new IllegalArgumentException("회원이 존재하지 않습니다. id : "+user.getId());
		});
		
		
		if(persistance.getOauth() == null || persistance.getOauth().equals("")) {
			String rawPassword = user.getPassword();
			String encPassword = encoder.encode(rawPassword); 
			persistance.setPassword(encPassword);
			persistance.setEmail(user.getEmail());
		}
		
		
		// 회원 수정 함수 종료 시(Service 종료 시) -> 트랜잭션 종료 -> commit(영구적)이 자동으로 된다.
	    // 영속화된 persistance 객체의 변화가 감지되면 더티체킹 되어 자동으로 DB에 update문을 날려준다.
	}

}
