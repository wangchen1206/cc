package com.hp.cc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.hp.cc.jwt.JwtAuthenticationRequest;
import com.hp.cc.jwt.JwtAuthenticationResponse;
import com.hp.cc.jwt.JwtTokenUtil;
import com.hp.cc.redis.RedisService;

@Service
public class AuthService {

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private RedisService redisService;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	public JwtAuthenticationResponse token(JwtAuthenticationRequest jwtAuthenticationRequest) {
		
		UserDetails userDetails = userDetailsService.loadUserByUsername(jwtAuthenticationRequest.getUsername());
		String token = jwtTokenUtil.generateToken(userDetails);
		
		//redis 做缓存
		redisService.addMap(jwtTokenUtil.getHeader(), userDetails.getUsername(), userDetails,jwtTokenUtil.getExpiration()/1000);
		return new JwtAuthenticationResponse(token);
		
	}
}
