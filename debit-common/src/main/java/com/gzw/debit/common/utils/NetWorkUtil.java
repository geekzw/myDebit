package com.gzw.debit.common.utils;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by qinyisheng on 16/9/27.
 */
public class NetWorkUtil {
    public static HttpServletRequest getRequest ()
    {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null)
        {
            return null;
        }
        return ((ServletRequestAttributes) requestAttributes).getRequest();
    }

    public static HttpServletResponse getResonse(){
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null)
        {
            return null;
        }
        return ((ServletRequestAttributes) requestAttributes).getResponse();
    }
}
