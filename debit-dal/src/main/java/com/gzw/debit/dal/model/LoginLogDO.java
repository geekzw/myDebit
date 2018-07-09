package com.gzw.debit.dal.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;
import java.time.LocalDate;

@Data
public class LoginLogDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     * debit_login_log.id
     */
    private Long id;

    /**
     * 用户id
     * debit_login_log.user_id
     */
    private Long userId;

    /**
     * 登录来源：1android，2ios，3web
     * debit_login_log.from_where
     */
    private Integer fromWhere;

    /**
     * 逻辑状态。1有效。0无效。
     * debit_login_log.status
     */
    private Integer status;

    /**
     * 创建时间
     * debit_login_log.gmt_create
     */
    private LocalDateTime gmtCreate;

    /**
     * 最近一次的修改时间
     * debit_login_log.gmt_modified
     */
    private LocalDateTime gmtModified;

}