package com.hp.cc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.hp.cc.redis.RedisService;
import com.hp.cc.security.jwt.JwtAuthenticationRequest;
import com.hp.cc.security.jwt.JwtAuthenticationResponse;
import com.hp.cc.security.jwt.JwtTokenUtil;

@Service
public class AuthService {

	
	@Autowired
	private RedisService redisService;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	public JwtAuthenticationResponse token(JwtAuthenticationRequest jwtAuthenticationRequest) {
		
		//从redis 取出
		UserDetails userDetails = redisService.getMapField(jwtTokenUtil.getHeader(), jwtAuthenticationRequest.getUsername());
		String token = jwtTokenUtil.generateToken(userDetails);
		return new JwtAuthenticationResponse(token);
		
	}
}
