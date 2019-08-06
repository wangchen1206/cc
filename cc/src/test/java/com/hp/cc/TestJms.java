package com.hp.cc;

import java.io.File;

import com.alibaba.fastjson.JSONObject;
import com.hp.cc.util.HttpUtils;

public class TestJms {
	
	
	public static void main(String[] args) {
		String jobWithFile = "http://localhost:8081/jms/jobWithFile";
		File file = new File("C:\\Users\\wachen\\Desktop\\42彩色图文DOC.doc");
		String result = HttpUtils.postFile(file, null, jobWithFile);
		JSONObject jsonObject = JSONObject.parseObject(result);
		System.out.println(jsonObject.get("id"));
	}
}
