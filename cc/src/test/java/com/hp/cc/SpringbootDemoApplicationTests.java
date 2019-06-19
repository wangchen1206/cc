package com.hp.cc;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.hp.cc.executor.service.ExecutorSercice;
import com.hp.cc.model.SysUser;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootDemoApplicationTests {

	
	
	
	@Autowired
	private ExecutorSercice executorSercice;
	
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
