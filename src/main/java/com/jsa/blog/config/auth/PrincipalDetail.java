package com.jsa.blog.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.jsa.blog.model.User;

import lombok.Getter;

@Getter
public class PrincipalDetail implements UserDetails{

	private User user;

	public PrincipalDetail(User user) {
		this.user=user;
	}
	
	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	// 계정 유효 기간 만료 여부 확인
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	// 사용자 계정 잠겨 있는지 여부
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	// 자격 증명(비밀번호)이 만료되었는지 여부
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	// 계정 활성화되었는지 여부
	@Override
	public boolean isEnabled() {
		return true;
	} 
	
	// 사용자에게 부여된 권한 목록을 반환
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		Collection<GrantedAuthority> collectros = new ArrayList<>();
		
		collectros.add(() -> { return "ROLE_"+user.getRole();});
		return collectros;
	}
	
}
