package com.gzw.debit.core.enums;

import com.gzw.debit.core.entry.Const;

/**
 * auth:gujian
 * time:2018/6/24
 * email:gujian@maihaoche.com
 * describe:
 */
public enum  ErrorEnum {
    SUCCESS(Const.SUCCESS,"请求成功"),
    SERVE_ERROR(Const.SERVER_ERROR,"服务器错误"),
    FORM_ERROR(Const.FORM_ERROR,"请求参数有误"),
    USER_EXIST(Const.USER_EXIST,"用户已存在"),
    USER_NOT_EXIST(Const.USER_NOT_EXIST,"用户不存在"),
    PASSWORD_ERROR(Const.PASSWORD_ERROR,"密码错误"),
    PLEASE_LOGIN(Const.PLEASE_LOGIN,"请先登录");

    private int code;
    private String desc;

    ErrorEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
