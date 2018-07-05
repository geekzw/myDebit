package com.gzw.debit.dal.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;
import java.time.LocalDate;

@Data
public class VersionSwitchDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * log_id
     * version_switch.id
     */
    private Long id;

    /**
     * 版本号
     * version_switch.version
     */
    private Integer version;

    /**
     * 是否打开线上开关
     * version_switch.open_online
     */
    private Integer openOnline;

    /**
     * 逻辑状态。1有效。0无效。
     * version_switch.status
     */
    private Integer status;

    /**
     * 创建时间
     * version_switch.gmt_create
     */
    private LocalDateTime gmtCreate;

    /**
     * 最近一次的修改时间
     * version_switch.gmt_modified
     */
    private LocalDateTime gmtModified;

}