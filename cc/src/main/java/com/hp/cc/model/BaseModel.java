package com.hp.cc.model;

import java.time.LocalDateTime;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author ck
 * @date 2019年6月20日 下午2:05:04
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class BaseModel {

	//将时间格式化
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	@TableField(value = "createDate")
	private LocalDateTime createDate;
	
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	@TableField(value = "updateDate")
	private LocalDateTime updateDate;
	
	
}
