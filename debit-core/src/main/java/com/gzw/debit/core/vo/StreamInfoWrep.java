package com.gzw.debit.core.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * auth:gujian
 * time:2018/7/13
 * email:gujian@maihaoche.com
 * describe:
 */
@Data
public class StreamInfoWrep implements Serializable{

    private List<StreamInfo> streamInfos;

    private Integer registerCount = 0;

    private Integer intentCount = 0;

    private Integer accurateCount = 0;

}
