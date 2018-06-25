package com.gzw.debit.core.ao.impl;

import com.gzw.debit.core.utils.StringUtil;
import com.gzw.debit.core.ao.RedisAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * auth:gujian
 * time:2018/5/26
 * email:gujian@maihaoche.com
 * describe:
 */
@Service
public class RedisAOImpl implements RedisAO{


    @Autowired
    private JedisPool jedisPool;

    @Override
    public Long incr(String key) {

        if(StringUtils.isEmpty(key)){
            return 0L;
        }
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            return jedis.incr(key);
        }catch (Exception e){
            return 0L;
        }finally {
            if(jedis!=null){
                jedis.close();
            }
        }

    }

    @Override
    public String get(String key) {
        if(StringUtils.isEmpty(key)){
            return null;
        }
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            return jedis.get(key);
        }catch (Exception e){
            return null;
        }finally {
            if(jedis!=null){
                jedis.close();
            }
        }
    }

    @Override
    public void setObject(String key, String json) {
        if(StringUtils.isEmpty(key)){
            return ;
        }
        if(StringUtils.isEmpty(json)){
            return;
        }
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            jedis.set(key,json);
        }catch (Exception e){
            return ;
        }finally {
            if(jedis!=null){
                jedis.close();
            }
        }
    }

    @Override
    public void setExpr(String key, int seconds) {
        if(StringUtil.isEmpty(key)){
            return;
        }
        if(seconds<=0){
            return;
        }
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            jedis.expire(key,seconds);
        }catch (Exception e){
            return ;
        }finally {
            if(jedis!=null){
                jedis.close();
            }
        }
    }

}
