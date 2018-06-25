package com.gzw.debit.core.form.base;

import lombok.Data;

import java.io.Serializable;

/**
 * auth:gujian
 * time:2018/6/24
 * email:gujian@maihaoche.com
 * describe:
 */
@Data
public class BaseForm implements Serializable{

    private String sessionId;
}
