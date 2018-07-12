package com.gzw.debit.core.vo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * auth:gujian
 * time:2018/7/12
 * email:gujian@maihaoche.com
 * describe:
 */
@Data
public class StreamInfo implements Serializable {

    /**
     * user_id
     * debit_user.id
     */
    private Long id;

    /**
     * 用户名
     * debit_user.username
     */
    private String username;


    /**
     * 注册来源：0.未知，1.android，2.ios，3.h5
     * debit_user.from_where
     */
    private Integer fromWhere;


    /**
     * 是否登录过：1是，0不是
     * debit_user.is_login
     */
    private Integer isLogin;

    /**
     * 首次登录时间
     * debit_user.fist_login_time
     */
    private String fistLoginTime;


    /**
     * 创建时间
     * debit_user.gmt_create
     */
    private String gmtCreate;

}
