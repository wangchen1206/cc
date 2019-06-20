package com.hp.cc.service;

import com.hp.cc.model.SysUser;

/**
 * @author ck
 * @date 2019年6月18日 下午4:56:09
 */
public interface UserService{

	public SysUser findByUserName(String username);
}
