package com.hp.cc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hp.cc.jwt.JwtAuthenticationRequest;
import com.hp.cc.jwt.JwtAuthenticationResponse;
import com.hp.cc.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
	private AuthService authService;

	@PostMapping("/token")
	public JwtAuthenticationResponse token(@RequestBody JwtAuthenticationRequest jwtAuthenticationRequest){
		JwtAuthenticationResponse token = authService.token(jwtAuthenticationRequest);
		return token;
	}
}
