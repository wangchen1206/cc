package com.hp.cc.common;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.ToString;

@Component
@ConfigurationProperties(prefix = "var")
@Data
@ToString
public class Var {

	private List<String> list;
	
}
