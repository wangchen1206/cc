package com.hp.cc.model;

import java.time.LocalDateTime;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author ck
 * @date 2019年2月27日 下午4:02:16
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
@Accessors(chain = true)
@ToString(callSuper = true,exclude = {"password"})
public class SysUser extends BaseModel{
	
	@TableId(value = "id",type = IdType.AUTO)
	private Integer id;
	private String username;
	private String password;
	private boolean enabled;
	@TableField(exist = false)
	private List<SysRole> roles;
	
	
}
