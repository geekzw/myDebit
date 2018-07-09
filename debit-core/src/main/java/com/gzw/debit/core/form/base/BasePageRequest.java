package com.gzw.debit.core.form.base;

import lombok.Data;

import java.io.Serializable;

/**
 * auth:gujian
 * time:2018/7/9
 * email:gujian@maihaoche.com
 * describe:
 */
@Data
public class BasePageRequest implements Serializable {

    public static final Integer DEFAULT_NO = 1;
    public static final Integer DEFAULT_SIZE = 20;

    private String sessionId;

    private Integer pageNo;

    private Integer pageSize;
}
