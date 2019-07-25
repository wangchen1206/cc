package com.hp.cc.security;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hp.cc.entity.SysRole;
import com.hp.cc.entity.SysUser;
import com.hp.cc.jwt.JwtTokenUtil;
import com.hp.cc.jwt.JwtUser;
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
	
	@Autowired
	private RedisService redisService;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Override
	public UserDetails loadUserByUsername(String username) {
		SysUser user = userService.findByUserName(username);
		if(user == null){
			throw new UsernameNotFoundException(String.format("用户名%s不存在", username));
		}else{
			List<SimpleGrantedAuthority> authorities = user.getRoles().stream()
					.map(SysRole::getName).map(SimpleGrantedAuthority::new)
					.collect(Collectors.toList());
			JwtUser jwtUser = new JwtUser(user.getUsername(), user.getPassword(), user.isEnabled(), authorities);
			redisService.addMap(jwtTokenUtil.getHeader(), username, jwtUser,jwtTokenUtil.getExpiration()/1000);
			return jwtUser;
		}
	}

}
