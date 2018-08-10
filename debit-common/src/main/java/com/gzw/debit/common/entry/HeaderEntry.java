package com.gzw.debit.common.entry;

import lombok.Data;

/**
 * auth:gujian
 * time:2018/7/22
 * email:gujian@maihaoche.com
 * describe:
 */
@Data
public class HeaderEntry {

    private String sessionId;

    private Integer deviceType;

    private Integer packageType;

    private String version;
}
