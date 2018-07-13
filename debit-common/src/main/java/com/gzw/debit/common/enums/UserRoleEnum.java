package com.gzw.debit.common.enums;

/**
 * auth:gujian
 * time:2018/7/13
 * email:gujian@maihaoche.com
 * describe:
 */
public enum UserRoleEnum {
    ROLE_ADMIN(0,"超级管理员"),
    ROLE_NORMAL(1,"普通商户"),
    ROLE_OPERATE(2,"运营");


    private int code;

    private String desc;

    UserRoleEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
