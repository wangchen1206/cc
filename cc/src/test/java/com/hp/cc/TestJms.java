package com.hp.cc;

import java.io.File;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.hp.cc.util.HttpUtils;

public class TestJms {
	
	
	public static void main(String[] args) {
		String jobWithFile = "http://localhost:8081/jms/jobWithFile";
		String jobWithUrl = "http://localhost:8081/jms/job";
		File file = new File("C:\\Users\\wachen\\Desktop\\sdf.doc");
		Map<String, String> params = Maps.newHashMap();
		params.put("inUrl", "https://s3.cn-east-1.jdcloud-oss.com/blue-oss/6a2e1d0302a19f864dc1c1a8e385bfdf.doc");
		String result = HttpUtils.postJson(JSONObject.toJSONString(params), jobWithUrl);
		JSONObject jsonObject = JSONObject.parseObject(result);
		System.out.println(jsonObject.get("id"));
	}
}
