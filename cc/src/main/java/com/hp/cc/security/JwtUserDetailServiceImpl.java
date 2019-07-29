package com.hp.cc.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hp.cc.entity.User;
import com.hp.cc.redis.RedisService;
import com.hp.cc.security.jwt.JwtTokenUtil;
import com.hp.cc.security.jwt.JwtUser;
import com.hp.cc.security.jwt.JwtUserFactory;
import com.hp.cc.service.IUserService;

/**
 * @author ck
 * @date 2019年6月18日 下午4:54:36
 */
@Service
public class JwtUserDetailServiceImpl implements UserDetailsService {

	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private RedisService redisService;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Override
	public UserDetails loadUserByUsername(String username) {
		User user = userService.findUserByUsername(username);
		if(user == null){
			throw new UsernameNotFoundException(String.format("用户名%s不存在", username));
		}else{
			if(!user.getEnabled()) throw new DisabledException(String.format("用户名%s的账户已停用", username));
			JwtUser jwtUser = JwtUserFactory.createJwtUser(user);
			redisService.addMap(jwtTokenUtil.getHeader(), username, jwtUser,jwtTokenUtil.getExpiration()/1000);
			return jwtUser;
		}
	}

}
