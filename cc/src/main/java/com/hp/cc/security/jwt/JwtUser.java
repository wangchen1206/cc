package com.hp.cc.security.jwt;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author ck
 * @date 2019年6月18日 下午2:52:35
 */
public class JwtUser implements UserDetails{
	
	private static final long serialVersionUID = 1L;
	private long id;
	private String username;
	private String name;
	private String email;
	@JSONField(serialize = false)
	private String password;
	private Boolean enabled;
	private Collection<? extends GrantedAuthority> authorities;
	private LocalDateTime lastPasswordResetDate;
	private LocalDateTime lastLoginDate;
	
	public JwtUser(long id, String username, String name, String email, String password, Boolean enabled,
			Collection<? extends GrantedAuthority> authorities, LocalDateTime lastPasswordResetDate,
			LocalDateTime lastLoginDate) {
		super();
		this.id = id;
		this.username = username;
		this.name = name;
		this.email = email;
		this.password = password;
		this.enabled = enabled;
		this.authorities = authorities;
		this.lastPasswordResetDate = lastPasswordResetDate;
		this.lastLoginDate = lastLoginDate;
	}
	
	

	public LocalDateTime getLastPasswordResetDate() {
		return lastPasswordResetDate;
	}



	public void setLastPasswordResetDate(LocalDateTime lastPasswordResetDate) {
		this.lastPasswordResetDate = lastPasswordResetDate;
	}



	public LocalDateTime getLastLoginDate() {
		return lastLoginDate;
	}



	public void setLastLoginDate(LocalDateTime lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public long getId() {
		return id;
	}



	public String getEmail() {
		return email;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	public static JwtUser getJwtUserFromContext() {
		try {
			return (JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return null;
		}
	}
	
	public static long currentUserId() {
		JwtUser jwtUser = getJwtUserFromContext();
		if(null != jwtUser) return jwtUser.getId();
		else return -2L;
	}
	
	public static String currentUserName() {
		JwtUser jwtUser = getJwtUserFromContext();
		if(null != jwtUser) return jwtUser.getUsername();
		else return "Anonymous";
	}
	
	public static List<String> currentUserRoles(){
		JwtUser jwtUser = getJwtUserFromContext();
		if(null != jwtUser) return jwtUser.getAuthorities().stream().map(authority->authority.getAuthority()).collect(Collectors.toList());
		else return Collections.emptyList();
	}

}
