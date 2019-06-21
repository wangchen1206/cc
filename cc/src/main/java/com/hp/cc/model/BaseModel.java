package com.hp.cc.model;

import java.time.LocalDateTime;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.Data;

/**
 * @author ck
 * @date 2019年6月20日 下午2:05:04
 */
@Data
public class BaseModel {

	//将时间格式化
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createDate;
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime updateDate;
	
	
}
