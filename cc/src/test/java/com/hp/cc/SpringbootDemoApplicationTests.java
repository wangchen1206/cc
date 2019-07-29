package com.hp.cc;

import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import com.hp.cc.entity.Msg;
import com.hp.cc.executor.service.ExecutorSercice;
import com.hp.cc.mapper.MsgDao;
import com.hp.cc.redis.RedisService;
import com.hp.cc.service.IProductService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootDemoApplicationTests {

	
	
	@Autowired
	private RedisService redisService;
	
	
	@Autowired
	private ExecutorSercice executorSercice;
	
	
	
	@Autowired
	private MsgDao msgDao;
	
	@Autowired
	private IProductService iProductService;
	
	@Test
	public void testProductService() {
//		Product product = new Product();
//		product.setProductName("cola");
//		product.setProductPrice(3.0);
//		product.setCreatedBy(1L);
//		product.setCreateTime(LocalDateTime.now());
//		iProductService.save(product);
		
		System.out.println(iProductService.list().get(0));
	}
	
	@Test
	public void testMsgDao(){
		Msg msg = Msg.builder().createDate(LocalDateTime.now()).title("msg3").content("content3").etraInfo("etraInfo2").build();
		msgDao.insert(msg);
		
//		 List<Msg> selectList = msgDao.selectList(null);
//		 selectList.forEach(System.out::println);;
	}
	
	@Test
	public void testMybatisPlus(){
//		Wrapper<SysUser> wrapper = new Wr
		
//		Page<SysUser> page = new Page<>();
//		page.setCurrent(2).setOptimizeCountSql(true).setSize(1);
//		page = (Page<SysUser>) userDao.selectPage(page, null);
//		page.getRecords().forEach(sysUser->System.out.println(sysUser.getUsername()));
	}
	
	@Test
	public void testUserService(){
	}
	
	@Test
	public void testRedis(){
		
		
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
