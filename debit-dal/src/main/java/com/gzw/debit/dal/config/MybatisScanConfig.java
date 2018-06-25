package com.gzw.debit.dal.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * auth:gujian
 * time:2018/6/25
 * email:gujian@maihaoche.com
 * describe:
 */
@Configuration
@MapperScan("com.gzw.debit.dal.mapper")
public class MybatisScanConfig {
}
