package com.gzw.debit.core.form;

import lombok.Data;

import java.io.Serializable;

/**
 * auth:gujian
 * time:2018/7/13
 * email:gujian@maihaoche.com
 * describe:
 */
@Data
public class EditAnalyzeRuleForm implements Serializable{

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
}
