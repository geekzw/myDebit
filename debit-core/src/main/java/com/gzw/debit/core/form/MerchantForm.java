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
public class MerchantForm implements Serializable {

    private String name;

    private Long id;

    private String username;

    private String password;

    private Integer type;
}
