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

    private Long productId;

    private Integer type;

    private Boolean isBanner;
}
