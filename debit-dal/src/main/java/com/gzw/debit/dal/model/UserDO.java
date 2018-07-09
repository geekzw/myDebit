package com.gzw.debit.dal.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;
import java.time.LocalDate;

@Data
public class UserDO implements Serializable {

    private static final long serialVersionUID = 1L;

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
     * 昵称
     * debit_user.nick_name
     */
    private String nickName;

    /**
     * 密码
     * debit_user.password
     */
    private String password;

    /**
     * 注册来源：0.未知，1.android，2.ios，3.h5
     * debit_user.from_where
     */
    private Integer fromWhere;

    /**
     * 用户来源：三方channelID,0表示自己平台
     * debit_user.channel_id
     */
    private Integer channelId;

    /**
     * 是否登录过：1是，0不是
     * debit_user.is_login
     */
    private Integer isLogin;

    /**
     * 首次登录时间
     * debit_user.fist_login_time
     */
    private LocalDateTime fistLoginTime;

    /**
     * 逻辑状态。1有效。0无效。
     * debit_user.status
     */
    private Integer status;

    /**
     * 创建时间
     * debit_user.gmt_create
     */
    private LocalDateTime gmtCreate;

    /**
     * 最近一次的修改时间
     * debit_user.gmt_modified
     */
    private LocalDateTime gmtModified;

}