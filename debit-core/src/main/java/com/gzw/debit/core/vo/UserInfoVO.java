package com.gzw.debit.core.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * auth:gujian
 * time:2018/7/6
 * email:gujian@maihaoche.com
 * describe:
 */
@Data
public class UserInfoVO implements Serializable{

    private String username;

    private Integer devicesType;

    private String registerTime;

    private Integer fromWhere;

}
