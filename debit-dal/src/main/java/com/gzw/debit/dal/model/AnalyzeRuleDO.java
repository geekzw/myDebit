package com.gzw.debit.dal.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;
import java.time.LocalDate;

@Data
public class AnalyzeRuleDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     * debit_analyze_rule.id
     */
    private Long id;

    /**
     * 列表点击次数
     * debit_analyze_rule.list_count
     */
    private Integer listCount;

    /**
     * 详情点击次数
     * debit_analyze_rule.detail_count
     */
    private Integer detailCount;

    /**
     * 类型：1是意向用户，2是精准用户
     * debit_analyze_rule.type
     */
    private Integer type;

    /**
     * 逻辑状态。1有效。0无效。
     * debit_analyze_rule.status
     */
    private Integer status;

    /**
     * 创建时间
     * debit_analyze_rule.gmt_create
     */
    private LocalDateTime gmtCreate;

    /**
     * 最近一次的修改时间
     * debit_analyze_rule.gmt_modified
     */
    private LocalDateTime gmtModified;

}