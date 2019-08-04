package com.hp.cc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.hp.cc.security.JwtAuthenticationTokenFilter;

/**
 * springsecurity config
 * 
 * @author ck
 * @date 2019年2月27日 下午4:43:28
 */
@Configuration
@EnableWebSecurity
// 开启 security注解生效
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	// AuthController 验证的时候用到
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}


	// // 处理未认证的request
	// @Autowired
	// private JwtAuthenticationEntryPoint unauthorizedHandler;
	//
	// //处理没有权限的request
	// @Autowired
	// private AccessDeniedEntryPoint accessDeniedEntryPoint;

	// 密码加密器 每次加密的密码都不一样
	@Bean
	BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// 添加jwt token验证过滤器
	@Bean
	public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() {
		return new JwtAuthenticationTokenFilter();
	}


	/*
	 * 定义请求规则 配置相关处理器，拦截器
	 */
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
				// 禁用csrf
				// .csrf().disable().exceptionHandling().accessDeniedHandler(accessDeniedEntryPoint).authenticationEntryPoint(unauthorizedHandler).and()
				.csrf().disable().exceptionHandling().and()
				// 不创建session
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.authorizeRequests().antMatchers("/api/**/**").permitAll()
				// 需验证了才能访问
				.antMatchers("/home/**").authenticated()
				// 其他放行
				.anyRequest().permitAll();

		// 添加 令牌 权限验证 拦截器，在认证拦截器之前
		httpSecurity.addFilterBefore(jwtAuthenticationTokenFilter(),
				UsernamePasswordAuthenticationFilter.class);

		// 禁用页面缓存
		httpSecurity.headers().cacheControl();
	}
}
