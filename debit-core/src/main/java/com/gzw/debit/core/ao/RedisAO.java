package com.gzw.debit.core.ao;

/**
 * auth:gujian
 * time:2018/5/26
 * email:gujian@maihaoche.com
 * describe:
 */
public interface RedisAO {

    Long incr(String key);

    String get(String key);

    void setObject(String key,String json);

    void setExpr(String key,int seconds);

}
