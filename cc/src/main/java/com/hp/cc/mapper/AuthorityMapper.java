package com.hp.cc.mapper;

import com.hp.cc.entity.Authority;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author cc
 * @since 2019-07-29
 */
public interface AuthorityMapper extends BaseMapper<Authority> {

	public List<Authority> selectByUserId(@Param("userId") Long userId);
}
