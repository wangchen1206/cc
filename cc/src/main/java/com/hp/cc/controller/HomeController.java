package com.hp.cc.controller;

import java.time.LocalDateTime;

import javax.validation.Valid;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hp.cc.common.Result;
import com.hp.cc.model.Msg;
import com.hp.cc.model.SysUser;

/**
 * @author ck
 * @date 2019年2月28日 上午11:46:14
 */
@RestController
public class HomeController {

	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@RequestMapping("/home/test")
	public Result index(){
		SysUser user = new SysUser();
		user.setUsername("scs");
		user.setCreateDate(LocalDateTime.now());
		return Result.createBySuccess(user);
	}
	
	@PostMapping("/home/test2")
	public Result test(@RequestBody @Valid Msg msg){
		msg.setEtraInfo("etraInfo");
		return Result.createBySuccess(msg);
	}
}
