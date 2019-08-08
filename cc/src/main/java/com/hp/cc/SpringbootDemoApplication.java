package com.hp.cc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.hp.cc.mapper")
public class SpringbootDemoApplication {
	
	

	public static void main(String[] args) {
		SpringApplication.run(SpringbootDemoApplication.class, args);
		
	}
	
	
	
}
