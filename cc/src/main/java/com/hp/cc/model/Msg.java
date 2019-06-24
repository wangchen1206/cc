package com.hp.cc.model;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
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
	
//	@TableField(value = "create_date")
	private LocalDateTime createDate;
	
	
	
}
