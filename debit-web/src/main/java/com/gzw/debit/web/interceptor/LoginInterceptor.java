package com.gzw.debit.web.interceptor;

import com.alibaba.fastjson.JSON;
import com.gzw.debit.common.entry.User;
import com.gzw.debit.common.utils.SpringContextUtil;
import com.gzw.debit.common.utils.ThreadPoolExecutorUtil;
import com.gzw.debit.core.ao.AliveAO;
import com.gzw.debit.core.ao.RedisAO;
import com.gzw.debit.core.ao.impl.AliveAOImpl;
import com.gzw.debit.core.ao.impl.RedisAOImpl;
import com.gzw.debit.core.enums.ErrorEnum;
import com.gzw.debit.core.enums.StatusEnum;
import com.gzw.debit.core.form.base.BaseResponse;
import com.gzw.debit.core.manager.DayAliveManager;
import com.gzw.debit.core.utils.DateUtil;
import com.gzw.debit.core.utils.StringUtil;
import com.gzw.debit.dal.model.DayAliveDO;
import com.gzw.debit.dal.query.DayAliveQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * auth:gujian
 * time:2018/6/25
 * email:gujian@maihaoche.com
 * describe:
 */
public class LoginInterceptor implements HandlerInterceptor {
    private static Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
    private static String SESSION_ID = "sessionId";
    private static String DEVICESTYPE = "devicesType";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String sessionId = request.getParameter(SESSION_ID);
        String dt = request.getHeader(DEVICESTYPE);


        int devicesType;
        if(StringUtil.isEmpty(dt)){
            devicesType = -1;
        }else{
            try{
                devicesType = Integer.valueOf(dt);
            }catch (Exception e){
                devicesType = -1;
            }
        }
        if(StringUtil.isEmpty(sessionId)){
            sessionId =request.getHeader(SESSION_ID);
        }

        if(sessionId!=null){
            RedisAO redisAO = SpringContextUtil.getBean(RedisAOImpl.class);
            User user = (User) redisAO.get(sessionId);
            if(devicesType == 1 || devicesType == 2){
                AliveAO aliveAO = SpringContextUtil.getBean(AliveAOImpl.class);
                aliveAO.insertAlive(user);
            }

            if(user != null){
                return true;
            }
        }
        BaseResponse baseResponse = BaseResponse.create(ErrorEnum.PLEASE_LOGIN);
        String baseR = JSON.toJSONString(baseResponse);
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Type", "text/html;charset=UTF-8");
        response.getWriter().write(baseR);
        response.getWriter().close();
        return false;
    }
}
