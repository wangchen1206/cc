package com.hp.cc.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author ck
 * @date 2019年2月27日 下午4:02:16
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class SysUser extends BaseModel{
	
	private Integer id;
	private String username;
	private String password;
	private boolean enabled;
	private List<SysRole> roles;



	

}
