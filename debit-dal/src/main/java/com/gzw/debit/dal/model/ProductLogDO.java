package com.gzw.debit.dal.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;
import java.time.LocalDate;

@Data
public class ProductLogDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     * debit_product_log.id
     */
    private Long id;

    /**
     * 产品id
     * debit_product_log.product_id
     */
    private Integer productId;

    /**
     * 用户id
     * debit_product_log.user_id
     */
    private Long userId;

    /**
     * 点击次数
     * debit_product_log.count
     */
    private Integer count;

    /**
     * 逻辑状态。1有效。0无效。
     * debit_product_log.status
     */
    private Integer status;

    /**
     * 创建时间
     * debit_product_log.gmt_create
     */
    private LocalDateTime gmtCreate;

    /**
     * 最近一次的修改时间
     * debit_product_log.gmt_modified
     */
    private LocalDateTime gmtModified;

    /**
     * 点击的列表还是详情1是列表 2是详情
     * debit_product_log.type
     */
    private Integer type;

}