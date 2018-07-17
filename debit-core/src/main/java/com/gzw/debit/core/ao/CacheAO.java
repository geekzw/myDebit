package com.gzw.debit.core.ao;

/**
 * auth:gujian
 * time:2018/7/17
 * email:gujian@maihaoche.com
 * describe:
 */
public interface CacheAO {

    /**
     * 判断ip是否被允许
     * @param ip
     * @return
     */
    boolean isIpAllow(String ip);

}
