package com.hp.cc.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * Swagger2 配置
 * 
 * @author wachen
 *
 */
@Configuration
@EnableSwagger2
@Profile("dev")
public class Swagger2Config {

	@Value("${spring.application.name}")
	private String applicationName;
	
	
	@Bean
	public Docket apiInfo() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("API")
				.apiInfo(appInfo())
				.select()
				.paths(PathSelectors.ant("/home/**")).build();
	}
	
	private ApiInfo appInfo() {
		return new ApiInfoBuilder()
				.title(applicationName+"API Online")
				.contact(new Contact("cc", "", "ck_chenwang@163.com"))
				.description("2019-07-25 cc first init")
				.version("0.0.1")
				.build();
	}
	
}
