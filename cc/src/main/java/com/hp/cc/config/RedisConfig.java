package com.hp.cc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.hp.cc.serializer.FastJson2JsonRedisSerializer;

/**
 * redis config
 * 
 * @author ck
 * @date 2019年6月20日 下午1:25:20
 */
@Configuration
public class RedisConfig {
	
	@Bean
	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory){
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(factory);
		//key的序列化采用StringRedisSerializer
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setHashKeySerializer(new StringRedisSerializer());
		//Value的序列化采用FastJson2JsonRedisSerializer
		redisTemplate.setValueSerializer(new FastJson2JsonRedisSerializer<>(Object.class));
		redisTemplate.setHashValueSerializer(new FastJson2JsonRedisSerializer<>(Object.class));
		redisTemplate.afterPropertiesSet();
		return redisTemplate;
	}
}
