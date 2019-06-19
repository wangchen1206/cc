package com.hp.cc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.hp.cc.jwt.JwtAuthenticationRequest;
import com.hp.cc.jwt.JwtAuthenticationResponse;
import com.hp.cc.jwt.JwtTokenUtil;

@Service
public class AuthService {

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	public JwtAuthenticationResponse token(JwtAuthenticationRequest jwtAuthenticationRequest) {
		
		UserDetails userDetails = userDetailsService.loadUserByUsername(jwtAuthenticationRequest.getUsername());
		String token = jwtTokenUtil.generateToken(userDetails);
		return new JwtAuthenticationResponse(token);
		
	}
}
