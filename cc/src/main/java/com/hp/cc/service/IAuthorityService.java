package com.hp.cc.service;

import com.hp.cc.entity.Authority;
import com.hp.cc.entity.enums.AuthorityName;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cc
 * @since 2019-07-29
 */
public interface IAuthorityService extends IService<Authority> {

	public List<Authority> selectByUserId(Long userId) ;

	public Authority getOrCreateAuthorityByName(AuthorityName authorityName);
	
	public Authority findAuthorityByName(AuthorityName authorityName);
}
