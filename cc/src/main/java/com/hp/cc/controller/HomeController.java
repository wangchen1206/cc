package com.hp.cc.controller;

import java.time.LocalDateTime;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerMapping;

import com.hp.cc.common.Result;
import com.hp.cc.entity.Msg;
import com.hp.cc.entity.SysUser;

/**
 * @author ck
 * @date 2019年2月28日 上午11:46:14
 */
@RestController
public class HomeController {
	
	@Value("${jwt.expiration}")
	private Integer num;

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
	
	@GetMapping("/home/msg/{title}")
	public Result test(@PathVariable String title,HttpServletRequest request){
		Map pathVariables = (Map) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		System.out.println(pathVariables.get("title"));
		return Result.createBySuccess("success");
	}
	
	@GetMapping("/home/msg")
	public Result test(HttpServletRequest request){
		
		System.out.println("num is "+ num);
		Map pathVariables = (Map) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		if(null == pathVariables) {
			System.out.println("false---"+false);
		}else {
			System.out.println(pathVariables.get("title"));
		}
		return Result.createBySuccess("success");
	}
}
