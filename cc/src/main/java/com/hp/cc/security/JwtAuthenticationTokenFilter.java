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
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.hp.cc.jwt.JwtTokenUtil;

/**
 * 拦截器 验证令牌的合法性,获取用户信息，存入SecurityContextHolder
 * 并且获取用户的权限,在需要权限的请求时检查用户是否有相应的权限
 * 
 * @author ck
 * @date 2019年6月18日 下午4:15:17
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

	private final Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token = request.getHeader(jwtTokenUtil.getHeader());
		if (!StringUtils.isEmpty(token)) {
			String username = jwtTokenUtil.getUsernameFromToken(token);
			logger.debug("checking authentication for user " + username
					+ " who is requesting..."
					+ String.format("%s %s from IP: %s", request.getMethod(),
							request.getRequestURI(), request.getRemoteAddr()));
			if (username != null && SecurityContextHolder.getContext()
					.getAuthentication() == null) {
				UserDetails userDetails = userDetailsService
						.loadUserByUsername(username);
				if (jwtTokenUtil.validateToken(token, userDetails)) {
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
			logger.debug("Anoymouse user who is requesting..."
					+ String.format("%s %s from IP: %s", request.getMethod(),
							request.getRequestURI(), request.getRemoteAddr()));
		}
		filterChain.doFilter(request, response);
	}

}
