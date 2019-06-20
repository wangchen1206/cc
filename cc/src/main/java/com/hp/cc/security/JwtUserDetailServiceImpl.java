package com.hp.cc.security;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hp.cc.jwt.JwtUser;
import com.hp.cc.model.SysRole;
import com.hp.cc.model.SysUser;
import com.hp.cc.redis.RedisService;
import com.hp.cc.service.UserService;

/**
 * @author ck
 * @date 2019年6月18日 下午4:54:36
 */
@Service
public class JwtUserDetailServiceImpl implements UserDetailsService {

	
	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		SysUser user = userService.findByUserName(username);
		
		List<SimpleGrantedAuthority> authorities = user.getRoles().stream()
				.map(SysRole::getName).map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
		return new JwtUser(user.getUsername(), user.getPassword(), user.isEnabled(), authorities);
	}

}
