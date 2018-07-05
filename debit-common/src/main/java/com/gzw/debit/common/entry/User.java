package com.gzw.debit.common.entry;

import lombok.Data;

import java.io.Serializable;

/**
 * auth:gujian
 * time:2018/6/27
 * email:gujian@maihaoche.com
 * describe:
 */
@Data
public class User implements Serializable{

    private String username;

    private String password;
}
