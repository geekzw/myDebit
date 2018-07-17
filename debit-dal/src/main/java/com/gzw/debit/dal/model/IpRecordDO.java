package com.gzw.debit.dal.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;
import java.time.LocalDate;

@Data
public class IpRecordDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     * debit_ip_record.id
     */
    private Long id;

    /**
     * ip
     * debit_ip_record.ip
     */
    private String ip;

    /**
     * 0黑名单，1白名单
     * debit_ip_record.type
     */
    private Integer type;

    /**
     * 逻辑状态。1有效。0无效。
     * debit_ip_record.status
     */
    private Integer status;

    /**
     * 创建时间
     * debit_ip_record.gmt_create
     */
    private LocalDateTime gmtCreate;

    /**
     * 最近一次的修改时间
     * debit_ip_record.gmt_modified
     */
    private LocalDateTime gmtModified;

}