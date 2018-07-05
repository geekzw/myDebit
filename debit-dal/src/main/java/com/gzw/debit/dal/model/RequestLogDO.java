package com.gzw.debit.dal.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;
import java.time.LocalDate;

@Data
public class RequestLogDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * log_id
     * request_log.id
     */
    private Long id;

    /**
     * 用户ip
     * request_log.address
     */
    private String address;

    /**
     * 请求接口
     * request_log.url
     */
    private String url;

    /**
     * 逻辑状态。1有效。0无效。
     * request_log.status
     */
    private Integer status;

    /**
     * 创建时间
     * request_log.gmt_create
     */
    private LocalDateTime gmtCreate;

    /**
     * 最近一次的修改时间
     * request_log.gmt_modified
     */
    private LocalDateTime gmtModified;

}