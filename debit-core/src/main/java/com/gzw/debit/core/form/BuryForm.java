package com.gzw.debit.core.form;

import lombok.Data;

import java.io.Serializable;

/**
 * auth:gujian
 * time:2018/7/9
 * email:gujian@maihaoche.com
 * describe:
 */
@Data
public class BuryForm implements Serializable{
    //产品id
    private Long productId;
    //产品类型 1列表，2详情
    private Integer type;
    //是否是banner
    private Boolean isBanner;
    //0是app，1是h5，2是小程序
    private Integer fromWhere;
}
