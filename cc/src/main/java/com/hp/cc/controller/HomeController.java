package com.hp.cc.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hp.cc.model.Msg;

/**
 * @author ck
 * @date 2019年2月28日 上午11:46:14
 */
@RestController
public class HomeController {

	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@RequestMapping("/home/test")
	public Msg index(){
		return new Msg("测试标题","测试内容","额外信息，只对管理员显示");
	}
}
