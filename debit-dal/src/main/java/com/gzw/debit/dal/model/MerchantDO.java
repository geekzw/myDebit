package com.gzw.debit.dal.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;
import java.time.LocalDate;

@Data
public class MerchantDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商家id
     * debit_merchant.id
     */
    private Long id;

    /**
     * 商家名称
     * debit_merchant.name
     */
    private String name;

    /**
     * 商家channel
     * debit_merchant.channel_id
     */
    private String channelId;

    /**
     * 逻辑状态。1有效。0无效。
     * debit_merchant.status
     */
    private Integer status;

    /**
     * 创建时间
     * debit_merchant.gmt_create
     */
    private LocalDateTime gmtCreate;

    /**
     * 最近一次的修改时间
     * debit_merchant.gmt_modified
     */
    private LocalDateTime gmtModified;

    /**
     * 商家用户名
     * debit_merchant.username
     */
    private String username;

    /**
     * 商家密码
     * debit_merchant.password
     */
    private String password;

    /**
     * 商家类型，0是超级管理员，1是普通商家
     * debit_merchant.type
     */
    private Integer type;

}