package com.hp.cc.entity.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

public enum DelFlag{
    YES("0", "有效"),
    NO("1", "删除");

    @EnumValue
    private final String value;
    private final String desc;

    
    DelFlag(final String value, final String desc) {
        this.value = value;
        this.desc = desc;
    }
    


    public String getValue() {
        return value;
    }

    @JsonValue
    public String getDesc() {
        return desc;
    }
}
