package com.hp.cc.model;

import java.time.LocalDateTime;
import java.util.Date;

import javax.validation.constraints.NotNull;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ck
 * @date 2019年2月27日 下午4:05:22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Msg{
	
	@NotNull(message="title不能为null")
	private String title;
	private String content;
	@JSONField(serialize = false)
	private String etraInfo;
	
//	private String createDate;
//	@TableField(value = "creatDate")
	private LocalDateTime createDate;
	
	
	
}
