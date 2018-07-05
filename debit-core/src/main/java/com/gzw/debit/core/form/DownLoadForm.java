package com.gzw.debit.core.form;

import lombok.Data;

import java.io.Serializable;

/**
 * auth:gujian
 * time:2018/7/5
 * email:gujian@maihaoche.com
 * describe:
 */
@Data
public class DownLoadForm implements Serializable{

    //1表示android，2表示ios
    private Integer type;
}
