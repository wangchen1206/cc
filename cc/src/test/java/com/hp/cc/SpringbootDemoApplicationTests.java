package com.hp.cc;

import java.time.LocalDateTime;
import java.util.Map;

import org.apache.tomcat.jni.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSON;
import com.hp.cc.executor.service.ExecutorSercice;
import com.hp.cc.model.SysUser;
import com.hp.cc.redis.RedisService;
import com.hp.cc.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootDemoApplicationTests {

	
	
	@Autowired
	private RedisService redisService;
	
	
	@Autowired
	private ExecutorSercice executorSercice;
	
	@Autowired
	private UserService userService;
	
	@Test
	public void testUserService(){
		SysUser user = userService.findByUserName("admin");
		System.out.println("------------------"+user.getCreateDate());
	}
	
	@Test
	public void testRedis(){
//		SysUser user = new SysUser();
//		user.setUsername("cc");
//		user.setCreateDate(LocalDateTime.now());
//		redisService.set("cc", user);
//		
//		System.out.println(user.getCreateDate());
//		
//		SysUser user2 = (SysUser) redisService.get("cc");
//		System.out.println(JSON.toJSONString(user2));
		
		redisService.addMap("token", "cc", "token1");
		redisService.addMap("token", "ck", "token2");
		
		Map<String, Object> map = redisService.getMap("token");
		for(String key:map.keySet()){
			System.out.println("token---------------"+map.get(key));
		}
		
		System.out.println(redisService.getMapField("token", "cc").toString());
	}
	
	
	public static void main(String[] args) {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		
		String encode = bCryptPasswordEncoder.encode("abel");
		
		System.out.println("----"+encode);
		
		boolean matches = bCryptPasswordEncoder.matches("abel", encode);
		
		System.out.println("------"+matches);
		
		
		
	}
	
	
	@Test
	public void testExecutorTask() throws Exception{
//		SysUser user1 = new SysUser();
//		user1.setUsername("admin_pms");
//		user1.setPassword("123");
//		SysUser user2 = new SysUser();
//		user2.setUsername("dodo1000@openfire.com");
//		user2.setPassword("123");
//		List<SysUser> users = new ArrayList<>();
//		users.add(user1);
//		users.add(user2);
//		for(SysUser user:users){
//			executorSercice.executeAsyncTask(user);
//		}
		for (int i = 0; i < 30; i++) {
			executorSercice.testTask1(i);
		}
	}
	
}
