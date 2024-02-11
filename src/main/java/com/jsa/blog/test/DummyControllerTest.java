package com.jsa.blog.test;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.yaml.snakeyaml.emitter.EmitterException;

import com.jsa.blog.model.RoleType;
import com.jsa.blog.model.User;
import com.jsa.blog.repository.UserRepository;

@RestController
public class DummyControllerTest {

	@Autowired
	private UserRepository userRepository;

	@DeleteMapping("/dummy/user/{id}")
	public ResponseEntity<String> delete(@PathVariable int id) {
		
		Optional<User> userOptional = userRepository.findById(id);
		
		if (userOptional.isPresent()) {
			userRepository.deleteById(id);
			return ResponseEntity.ok("사용자가 성공적으로 삭제되었습니다.");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("해당 아이디가 없습니다.");
		}
	}

	// save 함수는 id 전달하지 않으면 insert
	// id 전달할 때 해당 id에 대한 데이터가 있으면 update, 없으면 insert
	@Transactional
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User requestUser) {

		User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("수정에 실패하였습니다"));

		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());

		// userRepository.save(user);

		return user;
	}

	// http://localhost:8080/blog/dummy/users
	@GetMapping("/dummy/users")
	public List<User> list() {
		return userRepository.findAll();
	}

	// http://localhost:8080/blog/dummy/user
	@GetMapping("/dummy/user")
	public List<User> pageList(
			@PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
		Page<User> pagingUser = userRepository.findAll(pageable);

		List<User> users = pagingUser.getContent();
		return users;
	}

	// http://localhost:8080/blog/dummy/user/3
	@GetMapping("dummy/user/{id}")
	public User detail(@PathVariable int id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("해당 유저는 없습니다."));
		return user;
	}

	// http://localhost:8080/blog/dummy/join
	@PostMapping("/dummy/join")
	public String join(User user) {
		System.out.println("id: " + user.getId());
		System.out.println("username: " + user.getUsername());
		System.out.println("password: " + user.getPassword());
		System.out.println("email: " + user.getEmail());
		System.out.println("role: " + user.getRole());
		System.out.println("createDate: " + user.getCreateDate());

		user.setRole(RoleType.USER);
		userRepository.save(user);
		return "회원가입이 완료되었습니다.";
	}
}
