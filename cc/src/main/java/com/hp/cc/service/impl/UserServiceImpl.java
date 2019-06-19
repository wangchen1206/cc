package com.hp.cc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hp.cc.dao.UserDao;
import com.hp.cc.model.SysUser;
import com.hp.cc.service.UserService;

/**
 * @author ck
 * @date 2019年6月18日 下午4:56:56
 */
@Service
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;
	
	@Override
	public SysUser findByUserName(String username) {
		return userDao.findByUserName(username);
	}

}
