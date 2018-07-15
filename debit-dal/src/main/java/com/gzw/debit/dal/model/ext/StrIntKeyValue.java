package com.gzw.debit.dal.model.ext;

import lombok.Data;

import java.io.Serializable;

/**
 * auth:gujian
 * time:2018/7/15
 * email:gujian@maihaoche.com
 * describe:
 */
@Data
public class StrIntKeyValue implements Serializable{

    private Integer count;

    private String resultValue;

}
