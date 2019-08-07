package com.hp.cc.service.impl;

import com.hp.cc.entity.Authority;
import com.hp.cc.entity.enums.AuthorityName;
import com.hp.cc.mapper.AuthorityMapper;
import com.hp.cc.security.jwt.JwtUser;
import com.hp.cc.service.IAuthorityService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cc
 * @since 2019-07-29
 */
@Service
public class AuthorityServiceImpl extends ServiceImpl<AuthorityMapper, Authority> implements IAuthorityService {

	@Autowired
	private AuthorityServiceImpl authorityService;
	
	@Autowired
	private AuthorityMapper authorityMapper;
	
	@Override
	public List<Authority> selectByUserId(Long userId) {
		return authorityMapper.selectByUserId(userId);
	}

	@Override
	public Authority getOrCreateAuthorityByName(AuthorityName authorityName) {
		Authority authority = this.findAuthorityByName(authorityName);
		if(authority == null) {
			authority = new Authority();
			authority.setName(authorityName);
			authority.setCreatedBy(JwtUser.currentUserId());
			this.save(authority);
		}
		return authority;
	}

	@Override
	public Authority findAuthorityByName(AuthorityName authorityName) {
		return this.authorityService.getOne(new LambdaQueryWrapper<Authority>().eq(Authority::getName, authorityName.getValue()));
	}

}
