package com.gzw.debit.dal.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;
import java.time.LocalDate;

@Data
public class BuryDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     * debit_bury.id
     */
    private Long id;

    /**
     * 用户id
     * debit_bury.user_id
     */
    private Long userId;

    /**
     * 产品id
     * debit_bury.product_id
     */
    private Long productId;

    /**
     * 来源
     * debit_bury.form_where
     */
    private Integer formWhere;

    /**
     * 逻辑状态。1有效。0无效。
     * debit_bury.status
     */
    private Integer status;

    /**
     * 创建时间
     * debit_bury.gmt_create
     */
    private LocalDateTime gmtCreate;

    /**
     * 最近一次的修改时间
     * debit_bury.gmt_modified
     */
    private LocalDateTime gmtModified;

}