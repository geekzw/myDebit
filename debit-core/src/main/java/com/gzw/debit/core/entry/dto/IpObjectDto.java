package com.gzw.debit.core.entry.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * auth:gujian
 * time:2018/7/17
 * email:gujian@maihaoche.com
 * describe:
 */
@Data
public class IpObjectDto implements Serializable{

    private String ip;

    private boolean isAllow;
}
