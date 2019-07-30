package com.hp.cc.service.impl;

import com.hp.cc.entity.Authority;
import com.hp.cc.entity.UserAuthority;
import com.hp.cc.mapper.UserAuthorityMapper;
import com.hp.cc.security.jwt.JwtUser;
import com.hp.cc.service.IUserAuthorityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

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
public class UserAuthorityServiceImpl extends ServiceImpl<UserAuthorityMapper, UserAuthority> implements IUserAuthorityService {

	
	@Transactional
	@Override
	public void addUserAuthorityRelation(Long userId, List<Authority> authorities) {
		List<UserAuthority> list = authorities.stream().map(auth->{
			UserAuthority userAuthority = new UserAuthority();
			userAuthority.setUserId(userId);
			userAuthority.setAuthorityId(auth.getId());
			userAuthority.setCreatedBy(JwtUser.currentUserId());
			return userAuthority;
		}).collect(Collectors.toList());
		this.saveBatch(list);
	}

}
