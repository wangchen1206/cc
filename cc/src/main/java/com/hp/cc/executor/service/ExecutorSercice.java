package com.hp.cc.executor.service;

import java.util.concurrent.Executor;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


/**
 * @author ck
 * @date 2019年3月13日 下午5:01:05
 */
@Service
public class ExecutorSercice {

	
	@Resource(name="getAsyncExecutor")
	private Executor executor;
	
	
	@Async
	public void testTask1(Integer integer){
		System.out.println(Thread.currentThread().getName()+"任务"+integer);
	}
}
