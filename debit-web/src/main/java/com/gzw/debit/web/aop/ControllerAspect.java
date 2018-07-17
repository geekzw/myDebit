package com.gzw.debit.web.aop;

import com.gzw.debit.common.utils.IPUtil;
import com.gzw.debit.common.utils.SpringContextUtil;
import com.gzw.debit.core.ao.CacheAO;
import com.gzw.debit.core.ao.impl.CacheAOImpl;
import com.gzw.debit.core.form.base.BaseResponse;
import com.gzw.debit.core.manager.RequestLogManager;
import com.gzw.debit.core.manager.impl.RequestLogManagerImpl;
import com.gzw.debit.dal.model.RequestLogDO;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    private static final String EXPGETRESULTDATAPOINT = "execution(public * com.gzw.debit.web.controller..*.*(..))";
    private static final String VERSIONCONTROLLER = "execution(public * com.gzw.debit.web.controller.AppMainController.versionStatus(..))";


    @Autowired
    private CacheAO cacheAO;

    @Pointcut(EXPGETRESULTDATAPOINT)
    public void executeService(){

    }

    @Pointcut(VERSIONCONTROLLER)
    public void versionMethod(){

    }

    @Around("versionMethod()")
    public Object version(ProceedingJoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if(attributes!=null) {
            HttpServletRequest request = attributes.getRequest();
            String address = IPUtil.getIpAddrByRequest(request);
            boolean isAllow = cacheAO.isIpAllow(address);
            if(isAllow){
                try {
                    return joinPoint.proceed();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        }
        return BaseResponse.create(false);
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
                String address = IPUtil.getIpAddrByRequest(request);
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
