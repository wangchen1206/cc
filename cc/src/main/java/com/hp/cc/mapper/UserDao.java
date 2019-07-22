package com.hp.cc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hp.cc.entity.SysUser;

/**
 * @author ck
 * @date 2019年2月27日 下午4:08:10
 */
public interface UserDao extends BaseMapper<SysUser>{

	public SysUser findByUserName(String username);
	
	
}
