package com.hp.cc.test;

import java.util.List;
import java.util.Map;

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
	
	private List<Person> persons;
	
	private Person person;
	
	private String[] strArray;
	
	private Integer[] numArray;
	
	private List<Map<String, String>> valMapList;
	
}
