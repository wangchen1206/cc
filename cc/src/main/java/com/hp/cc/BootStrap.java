package com.hp.cc;

import java.time.LocalDateTime;
import java.util.Arrays;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.hp.cc.config.InitUserConfiguration;
import com.hp.cc.entity.Authority;
import com.hp.cc.entity.User;
import com.hp.cc.entity.enums.AuthorityName;
import com.hp.cc.service.IAuthorityService;
import com.hp.cc.service.IUserService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class BootStrap {
	
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IAuthorityService authorityService;
	
	@Autowired
	private InitUserConfiguration initUserConfig;
	
	/**
	 * 在服务器启动之后运行，init data.只能被服务器调用一次
	 */
	@PostConstruct
	private void initUser() {
		
		
		log.info("BootStrap init users");
//		initRoles();
//		initUsers();
	}
	
	private void initUsers() {
		log.info("Bootstrap user accounts");
		initUserConfig.getUsers().stream().forEach(u->{
			log.info("init: "+u.getUser());
			Authority authority = this.authorityService.findAuthorityByName(AuthorityName.getEnum(u.getRole()));
			User user = this.userService.findUserByUsername(u.getUser());
			if(user == null) {
				user = new User();
				user.setUsername(u.getUser());
				user.setPassword(new BCryptPasswordEncoder().encode(u.getPassword()));
				user.setAuthorities(Arrays.asList(authority));
				user.setName(u.getUser());
				user.setEmail(u.getUser()+"@cc.com");
				user.setEnabled(true);
				user.setLastPasswordResetDate(LocalDateTime.now());
				this.userService.saveUser(user);
			}
			
		});
	}

	private void initRoles() {
		for (AuthorityName authorityName : AuthorityName.values()) {
			log.info("authorityName is :  "+authorityName);
			authorityService.getOrCreateAuthorityByName(authorityName);
		}
	}

	/**
	 * 在服务器卸载之后运行，destroy。只能被服务器调用一次
	 */
	@PreDestroy
	private void destroy() {
		log.warn("----------------------  BootStrap destroy");
	}
	
}
