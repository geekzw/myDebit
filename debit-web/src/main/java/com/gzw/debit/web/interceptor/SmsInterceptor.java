package com.gzw.debit.web.interceptor;

import com.alibaba.fastjson.JSON;
import com.gzw.debit.common.utils.PhoneFormatCheckUtil;
import com.gzw.debit.common.utils.SpringContextUtil;
import com.gzw.debit.core.ao.RedisAO;
import com.gzw.debit.core.ao.impl.RedisAOImpl;
import com.gzw.debit.core.entry.Const;
import com.gzw.debit.core.enums.ErrorEnum;
import com.gzw.debit.core.form.base.BaseResponse;
import com.gzw.debit.core.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * auth:gujian
 * time:2018/6/25
 * email:gujian@maihaoche.com
 * describe:
 */
public class SmsInterceptor implements HandlerInterceptor {
    private static Logger logger = LoggerFactory.getLogger(SmsInterceptor.class);
    private static String PHONE = "phone";
    private static String URL = "/getSmsCode.json";
    private static String VALUE = "value";
    private static String COUNT = "count";
    private static int MAX_COUNT = 10;
    private static long COUNT_EXPR = 60*60*24;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        BaseResponse baseResponse;
        RedisAO redisAO = SpringContextUtil.getBean(RedisAOImpl.class);
        String phone = request.getParameter(PHONE);
        if(StringUtil.isEmpty(phone)||!PhoneFormatCheckUtil.checkCellphone(phone)){

            if(StringUtil.isEmpty(phone)){
                logger.error("手机号码不能为空");
                baseResponse = BaseResponse.create(Const.PARAMS_ERROR,"手机号码不能为空");

            }else{
                logger.error("手机号码不合法:"+phone);
                baseResponse = BaseResponse.create(Const.PARAMS_ERROR,"手机号码不合法");
            }
            sendResponse(response,baseResponse);
            return false;
        }

        String countKey = phone+COUNT;
        int count = Integer.valueOf(redisAO.get(countKey) == null?"0":redisAO.get(countKey)+"");
        if(count > MAX_COUNT){
            logger.error("24小时内超过10次获取验证码:"+phone);
            baseResponse = BaseResponse.create(Const.SERVER_ERROR,"服务器异常");
            sendResponse(response,baseResponse);
        }else{
            count++;
            redisAO.set(countKey,count+"");
            if(count == 1){
                redisAO.set(countKey,count,COUNT_EXPR);
            }
        }

        String key = phone+URL;
        String phoneCode = (String) redisAO.get(key);
        if(StringUtil.isEmpty(phoneCode)){
            redisAO.set(key,VALUE,60);
            return true;
        }
        logger.error("1分钟内连续请求验证码:"+phone);
        baseResponse = BaseResponse.create(Const.LOGIC_ERROR,"服务器忙，请稍后在试");
        sendResponse(response,baseResponse);
        return false;
    }

    private void sendResponse(HttpServletResponse response,BaseResponse baseResponse)throws Exception{
        String content = JSON.toJSONString(baseResponse);
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Type", "text/html;charset=UTF-8");
        response.getWriter().write(content);
        response.getWriter().close();
    }
}
