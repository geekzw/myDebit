package com.gzw.debit.web.aop;

import com.gzw.debit.core.entry.Const;
import com.gzw.debit.core.form.base.BaseResponse;
import com.gzw.debit.core.utils.CheckUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * auth:gujian
 * time:2018/7/14
 * email:gujian@maihaoche.com
 * describe:
 */
@Component
@Aspect
public class AdminAspect {

    @Pointcut("@annotation(com.gzw.debit.common.annotation.Admin)")
    public void userAccess() {}
    @Around("userAccess()")
    public Object aroundMethod(ProceedingJoinPoint joinPoint){
        BaseResponse response = CheckUtil.checkPermision();
        if(!response.isSuccess()){
            return response;
        }
        try {
            return joinPoint.proceed();
        } catch (Throwable throwable) {
            response = BaseResponse.create(Const.SERVER_ERROR,"系统异常");
        }

        return response;

    }

}
