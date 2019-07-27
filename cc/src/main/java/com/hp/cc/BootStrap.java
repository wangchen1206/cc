package com.hp.cc;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class BootStrap {

	/**
	 * 在服务器启动之后运行，init data.只能被服务器调用一次
	 */
	@PostConstruct
	private void initUser() {
		log.info("----------------------  BootStrap init data");
	}
	
	@PostConstruct
	private void initUser1() {
		log.info("----------------------  BootStrap init data1");
	}
	
	/**
	 * 在服务器卸载之后运行，destroy。只能被服务器调用一次
	 */
	@PreDestroy
	private void destroy() {
		log.warn("----------------------  BootStrap destroy");
	}
	
	@PreDestroy
	private void destroy1() {
		log.warn("----------------------  BootStrap destroy1");
	}
}
