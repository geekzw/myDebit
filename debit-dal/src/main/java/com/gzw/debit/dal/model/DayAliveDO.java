package com.gzw.debit.dal.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;
import java.time.LocalDate;

@Data
public class DayAliveDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     * debit_day_alive.id
     */
    private Long id;

    /**
     * 用户名称
     * debit_day_alive.user_id
     */
    private Long userId;

    /**
     * 当日操作次数
     * debit_day_alive.count
     */
    private Integer count;

    /**
     * 逻辑状态。1有效。0无效。
     * debit_day_alive.status
     */
    private Integer status;

    /**
     * 创建时间
     * debit_day_alive.gmt_create
     */
    private LocalDateTime gmtCreate;

    /**
     * 最近一次的修改时间
     * debit_day_alive.gmt_modified
     */
    private LocalDateTime gmtModified;

    /**
     * 日活日期
     * debit_day_alive.alive_date
     */
    private LocalDate aliveDate;

    /**
     * 包类型
     * debit_day_alive.packagetype
     */
    private Integer packagetype;

}