package com.gzw.debit.core.utils;

import com.alibaba.fastjson.JSON;
import com.gzw.debit.common.entry.User;
import com.gzw.debit.common.utils.SpringContextUtil;
import com.gzw.debit.common.utils.WebSessionUtil;
import com.gzw.debit.core.ao.RedisAO;
import com.gzw.debit.core.ao.impl.RedisAOImpl;
import com.gzw.debit.dal.model.UserDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * auth:gujian
 * time:2018/6/27
 * email:gujian@maihaoche.com
 * describe:
 */
public class UserUtil {

    private static Logger logger = LoggerFactory.getLogger(UserUtil.class);
    public static User getUser(){
        RedisAO redisAO = SpringContextUtil.getBean(RedisAOImpl.class);
        User user = null;
        String sessionId = WebSessionUtil.getSessionId();
        if(StringUtil.isEmpty(sessionId)){
            logger.error("sessionId为空");
            return user;
        }
        try{
            user = (User) redisAO.get(sessionId);
            if(StringUtil.isEmpty(sessionId)){
                logger.error("redis中获取不到用户信息");
                return user;
            }
        }catch (Exception e){
            logger.error("从redis解析用户信息出错",e);
            return user;
        }
        return user;
    }

    public static User getUser(String sessionId){
        RedisAO redisAO = SpringContextUtil.getBean(RedisAOImpl.class);
        User user = null;
        try{
            user = (User) redisAO.get(sessionId);
            if(StringUtil.isEmpty(sessionId)){
                logger.error("redis中获取不到用户信息");
                return user;
            }
        }catch (Exception e){
            logger.error("从redis解析用户信息出错",e);
            return user;
        }
        return user;
    }


}
