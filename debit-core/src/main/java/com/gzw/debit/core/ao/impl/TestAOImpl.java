package com.gzw.debit.core.ao.impl;

import com.gzw.debit.core.ao.TestAO;
import com.gzw.debit.core.form.base.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
public class TestAOImpl implements TestAO {

    @Autowired
    private JedisPool jedisPool;


    @Override
    public BaseResponse<String> testRedis() {

        Jedis jedis = jedisPool.getResource();
        if(jedis == null){
            return BaseResponse.create(0,"jedis获取失败");
        }
        int key = (int) Math.random();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String result = jedis.setex(key+"", 60*10, "fsdfsdfsdfds");
        jedis.close();
        return BaseResponse.create(result);
    }
}
