package com.hp.cc.serializer;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.nio.charset.Charset;

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;

/**
 * redis fastjson序列化器
 * 
 * @author ck
 * @param <T>
 * @date 2019年6月20日 下午1:45:39
 */
public class FastJson2JsonRedisSerializer<T> implements RedisSerializer<T>{

	public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
	
	private Class<T> clazz;
	
	public FastJson2JsonRedisSerializer(Class<T> clazz) {
		super();
		// 巨坑！！！Fastjson关于autoType is not support解决 加入自动类型支持
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
		this.clazz = clazz;
	}

	@Override
	public T deserialize(byte[] bytes) throws SerializationException {
		if(bytes == null || bytes.length <= 0){
			return null;
		}
		String str = new String(bytes,DEFAULT_CHARSET);
		return JSON.parseObject(str, clazz);
	}

	@Override
	public byte[] serialize(T t) throws SerializationException {
		if(t == null){
			return new byte[0];
		}
		return JSON.toJSONString(t,SerializerFeature.WriteClassName).getBytes(DEFAULT_CHARSET);
	}

}
