package com.gzw.debit.web.aop;

import com.alibaba.fastjson.JSON;
import com.gzw.debit.core.entry.Const;
import com.gzw.debit.core.enums.ErrorEnum;
import com.gzw.debit.core.form.base.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

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
    public void defaultErrorHandler(HttpServletRequest request,HttpServletResponse response, Exception e)  {
        //打印异常信息：
        e.printStackTrace();
        String errMsg = e.getMessage()+"---URL : " + request.getRequestURL().toString();
        logger.error(errMsg);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode(Const.SERVER_ERROR);
        baseResponse.setDesc(errMsg);
        String baseR = JSON.toJSONString(baseResponse);
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        try {
            response.getWriter().write(baseR);
            response.getWriter().close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

}
