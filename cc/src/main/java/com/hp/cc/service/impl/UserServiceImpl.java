package com.hp.cc.service.impl;

import com.hp.cc.entity.Authority;
import com.hp.cc.entity.User;
import com.hp.cc.mapper.UserMapper;
import com.hp.cc.service.IAuthorityService;
import com.hp.cc.service.IUserAuthorityService;
import com.hp.cc.service.IUserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

import org.apache.http.util.Asserts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cc
 * @since 2019-07-29
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

	@Autowired
	private IAuthorityService authorityService;
	
	@Autowired
	private IUserAuthorityService userAuthorityService;
	
	@Override
	public User findUserByUsername(String username) {
		User user = this.getOne(new LambdaQueryWrapper<User>().eq(User::getUsername, username));
		if(user == null) return null;
		List<Authority> authorities = authorityService.selectByUserId(user.getId());
		user.setAuthorities(authorities);
		return user;
	}

	@Transactional(rollbackFor = Exception.class)
	public void saveUser(User user) {
		Asserts.check(this.findUserByUsername(user.getUsername()) == null, "重复的用户名：%s", user.getUsername());
		this.save(user);
		userAuthorityService.addUserAuthorityRelation(user.getId(),user.getAuthorities());
	}

}
