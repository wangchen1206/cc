package com.hp.cc.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.hp.cc.jwt.JwtTokenUtil;
import com.hp.cc.redis.RedisService;

import lombok.extern.slf4j.Slf4j;

/**
 * 拦截器 验证令牌的合法性,获取用户信息，存入SecurityContextHolder
 * 并且获取用户的权限,在需要权限的请求时检查用户是否有相应的权限
 * 
 * @author ck
 * @date 2019年6月18日 下午4:15:17
 */
@Slf4j
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

	
	private final String BEARER = "Bearer";
	
	@Autowired
	private RedisService redisService;


	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token = request.getHeader(jwtTokenUtil.getHeader());
		log.info("token is :"+token);
		if (!StringUtils.isEmpty(token)) {
			String tokenExpected = token.substring(BEARER.length());
			String username = jwtTokenUtil.getUsernameFromToken(tokenExpected);
			log.debug("checking authentication for user " + username
					+ " who is requesting..."
					+ String.format("%s %s from IP: %s", request.getMethod(),
							request.getRequestURI(), request.getRemoteAddr()));
			if (username != null && SecurityContextHolder.getContext()
					.getAuthentication() == null) {
				//从redis中获取userDetail
				UserDetails userDetails = redisService.getMapField(jwtTokenUtil.getHeader(), username);
				//每次请求都要校验token.
				if (jwtTokenUtil.validateToken(tokenExpected, userDetails)) {
					// 将用户信息，权限 存入 authentication ，方便后续校验
					UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
							username, null, userDetails.getAuthorities());
					authentication
							.setDetails(new WebAuthenticationDetailsSource()
									.buildDetails(request));
					// 将authentication 存入ThreadLocal,方便后续获取用户信息
					SecurityContextHolder.getContext()
							.setAuthentication(authentication);
				}
			}
		} else {
			log.debug("Anoymouse user who is requesting..."
					+ String.format("%s %s from IP: %s", request.getMethod(),
							request.getRequestURI(), request.getRemoteAddr()));
		}
		filterChain.doFilter(request, response);
	}

}
