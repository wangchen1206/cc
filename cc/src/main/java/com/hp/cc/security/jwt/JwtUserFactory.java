package com.hp.cc.security.jwt;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.hp.cc.entity.Authority;
import com.hp.cc.entity.User;

public final class JwtUserFactory {
	private JwtUserFactory() {};
	
	public static JwtUser createJwtUser(User user) {
		 return new JwtUser(
				 user.getId(),
				 user.getUsername(),
				 user.getName(),
				 user.getEmail(),
				 user.getPassword(),
				 user.getEnabled(),
				 mapToGrantedAuthorities(user.getAuthorities()),
				 user.getLastPasswordResetDate(),
				 user.getLastLoginDate()
				 );
	}

	private static List<GrantedAuthority> mapToGrantedAuthorities(List<Authority> authorities) {
		return authorities.stream().map(authority->new SimpleGrantedAuthority(authority.getName().name())).collect(Collectors.toList());
	}
}
