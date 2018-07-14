package com.gzw.debit.web.aop;

import com.gzw.debit.common.utils.SpringContextUtil;
import com.gzw.debit.core.manager.RequestLogManager;
import com.gzw.debit.core.manager.impl.RequestLogManagerImpl;
import com.gzw.debit.dal.model.RequestLogDO;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * auth:gujian
 * time:2018/6/26
 * email:gujian@maihaoche.com
 * describe:
 */
@Component
@Aspect
public class ControllerAspect {
    private static final Logger logger = LoggerFactory.getLogger(ControllerAspect.class);
    private static final String ExpGetResultDataPoint = "execution(public * com.gzw.debit.web.controller..*.*(..))";

    @Pointcut(ExpGetResultDataPoint)
    public void executeService(){

    }

    @Before("executeService()")
    public void doBeforeMethod(JoinPoint joinPoint){
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if(attributes!=null){
            HttpServletRequest request = attributes.getRequest();
            if(request!=null){
                // 记录下请求内容
                logger.info("URL : " + request.getRequestURL().toString());
                logger.info("HTTP_METHOD : " + request.getMethod());
                logger.info("IP : " + request.getRemoteAddr());
                logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
                logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
                logger.info(request.getHeader("sessionId"));
                logger.info(request.getHeader("devicesType"));
                logger.info("插入请求日志记录...");
                RequestLogManager requestLogManager = SpringContextUtil.getBean(RequestLogManagerImpl.class);
                String address = request.getRemoteAddr();
                String url = request.getRequestURI().toString();
                RequestLogDO requestLogDO = new RequestLogDO();
                requestLogDO.setAddress(address);
                requestLogDO.setUrl(url);
                long col = requestLogManager.insertSelective(requestLogDO);
                if(col < 1){
                    logger.error("插入请求日志记录失败");
                }
            }

        }
    }

    @AfterReturning(returning = "ret", pointcut = "executeService()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        logger.info("RESPONSE : " + ret);
    }
}
