package com.gzw.debit.dal.query.ext;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * auth:gujian
 * time:2018/7/15
 * email:gujian@maihaoche.com
 * describe:
 */
@Data
public class AliveDataQuery {

    private LocalDateTime startTime;

    private LocalDateTime endTime;
}
