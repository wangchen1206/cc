package com.hp.cc.service;

import com.hp.cc.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cc
 * @since 2019-07-29
 */
public interface IUserService extends IService<User> {

	public User findUserByUsername(String username) ;
}
