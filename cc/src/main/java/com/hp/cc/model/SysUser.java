package com.hp.cc.model;

import java.util.List;

/**
 * @author ck
 * @date 2019年2月27日 下午4:02:16
 */
public class SysUser{
	
	private Integer id;
	private String username;
	private String password;
	private boolean enabled;

	private List<SysRole> roles;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public List<SysRole> getRoles() {
		return roles;
	}

	public void setRoles(List<SysRole> roles) {
		this.roles = roles;
	}

	public SysUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SysUser(Integer id, String username, String password,
			boolean enabled, List<SysRole> roles) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "SysUser [id=" + id + ", username=" + username + ", password="
				+ password + ", enabled=" + enabled + ", roles=" + roles + "]";
	}

}
