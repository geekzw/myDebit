package com.gzw.debit.dal.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;
import java.time.LocalDate;

@Data
public class DownUrlDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     * debit_down_url.id
     */
    private Long id;

    /**
     * 类型：1android，2ios
     * debit_down_url.type
     */
    private Integer type;

    /**
     * 下载地址
     * debit_down_url.url
     */
    private String url;

    /**
     * 逻辑状态。1有效。0无效。
     * debit_down_url.status
     */
    private Integer status;

    /**
     * 创建时间
     * debit_down_url.gmt_create
     */
    private LocalDateTime gmtCreate;

    /**
     * 最近一次的修改时间
     * debit_down_url.gmt_modified
     */
    private LocalDateTime gmtModified;

}