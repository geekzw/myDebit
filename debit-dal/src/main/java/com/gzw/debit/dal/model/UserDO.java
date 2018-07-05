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

    /**
     * 注册来源：0.未知，1.android，2.ios，3.h5
     * debit_user.from_where
     */
    private Integer fromWhere;

}