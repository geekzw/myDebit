package com.gzw.debit.web.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * auth:gujian
 * time:2018/7/6
 * email:gujian@maihaoche.com
 * describe:
 */

@ControllerAdvice
public class GlobalDefaultExceptionHandler {

    private static Logger logger = LoggerFactory.getLogger(GlobalDefaultExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    public void defaultErrorHandler(HttpServletRequest req, Exception e)  {

        //打印异常信息：
        e.printStackTrace();
        logger.error(e.getMessage());

    }

}
