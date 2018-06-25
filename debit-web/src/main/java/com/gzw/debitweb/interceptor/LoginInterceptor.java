package com.gzw.debitweb.interceptor;

import com.alibaba.fastjson.JSON;
import com.gzw.debit.core.ao.RedisAO;
import com.gzw.debit.core.ao.impl.RedisAOImpl;
import com.gzw.debit.core.enums.ErrorEnum;
import com.gzw.debit.core.form.base.BaseResponse;
import com.gzw.debit.core.utils.SpringContextUtil;
import com.gzw.debit.core.utils.StringUtil;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * auth:gujian
 * time:2018/6/25
 * email:gujian@maihaoche.com
 * describe:
 */
public class LoginInterceptor implements HandlerInterceptor {
    private static String SESSION_ID = "sessionId";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String sessionId = request.getParameter(SESSION_ID);
        if(StringUtil.isEmpty(sessionId)){
            request.getHeader(SESSION_ID);
        }
        if(sessionId!=null){
            RedisAO redisAO = SpringContextUtil.getBean(RedisAOImpl.class);
            String userDO = redisAO.get(sessionId);
            if(userDO != null){
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
