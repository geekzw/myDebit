package com.gzw.debit.core.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * auth:gujian
 * time:2018/7/10
 * email:gujian@maihaoche.com
 * describe:
 */
@Data
public class PcLoginInfoVO implements Serializable {

    private Long id;

    private String channelId;

    private String name;

    private String username;

    private String password;

    private Integer type;

    private String SessionId;
}
