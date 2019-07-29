package com.hp.cc;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hp.cc.config.InitUserConfiguration;
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
		initRoles();
		initUsers();
	}
	
	private void initUsers() {
		
	}

	private void initRoles() {
		for (AuthorityName authorityName : AuthorityName.values()) {
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
