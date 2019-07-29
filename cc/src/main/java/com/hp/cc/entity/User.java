package com.hp.cc.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hp.cc.entity.BaseEntity;
import com.hp.cc.entity.enums.DelFlag;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author cc
 * @since 2019-07-29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="User对象", description="")
@ToString(callSuper = true,includeFieldNames = true)
public class User extends BaseEntity {

    private static final long serialVersionUID = 1L;

   // @TableLogic
    @JsonIgnore
    @ApiModelProperty(value = "删除标志")
    private String delFlag;
    
    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "是否启用")
    private Boolean enabled;

    @ApiModelProperty(value = "最后登录日期")
    private LocalDateTime lastLoginDate;

    @ApiModelProperty(value = "最后登录IP")
    private String lastLoginIp;

    @ApiModelProperty(value = "最后密码重置日期")
    private LocalDateTime lastPasswordResetDate;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "手机号/用户名")
    private String username;

    @ApiModelProperty(value = "入职日期")
    private LocalDate onboardDate;

    @ApiModelProperty(value = "离职日期")
    private LocalDate lastDate;


    @JsonIgnore
    private transient List<Authority> authorities;
    
    private transient List<String> authorityNames;
}
