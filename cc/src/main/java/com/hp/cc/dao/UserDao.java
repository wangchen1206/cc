package com.hp.cc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hp.cc.model.SysUser;

/**
 * @author ck
 * @date 2019年2月27日 下午4:08:10
 */
public interface UserDao extends BaseMapper<SysUser>{

	public SysUser findByUserName(String username);
	
	
}
