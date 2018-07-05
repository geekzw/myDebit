package com.gzw.debit.core.form;

import lombok.Data;

import java.io.Serializable;

/**
 * auth:gujian
 * time:2018/6/24
 * email:gujian@maihaoche.com
 * describe:
 */
@Data
public class LoginForm implements Serializable{

    private String username;
    private String password;
    private String verifyCode;
    //1.android 2.ios 3.h5
    private Integer formWhere;
}
