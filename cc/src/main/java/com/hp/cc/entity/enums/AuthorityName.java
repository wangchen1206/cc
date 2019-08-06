package com.hp.cc.entity.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;

public enum AuthorityName implements IEnum<String>{

	ROLE_MANAGER("ROLE_MANAGER", "管理员"), ROLE_GUEST("ROLE_GUEST", "客户"), ROLE_SUPER_ADMIN("ROLE_SUPER_ADMIN", "超级管理员");

	@EnumValue
	private final String value;
	private final String desc;

	AuthorityName(String value, String desc) {
		this.value = value;
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}

	@JsonValue
	public String getValue() {
		return value;
	}

	public static AuthorityName getEnum(String value) {
		for (AuthorityName authorityName : AuthorityName.values()) {
			if (authorityName.getValue().equalsIgnoreCase(value.trim())) {
				return authorityName;
			}
		}
		return null;
	}
	
	
}
