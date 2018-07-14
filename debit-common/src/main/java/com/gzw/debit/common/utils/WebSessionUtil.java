package com.gzw.debit.common.utils;


import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

/**
 * Created by qinyisheng on 16/9/27.
 */
public class WebSessionUtil {

    private static final String SESSION_ID = "sessionId";
    private static final String DEVICES_TYPE = "devicesType";



    public static String getSessionId() {
        String sessionId = NetWorkUtil.getRequest().getHeader(SESSION_ID);
        if(StringUtils.isEmpty(sessionId)){
            sessionId = NetWorkUtil.getRequest().getParameter(SESSION_ID);
        }
        return sessionId;
    }

    public static String createSessionId(){
        return NetWorkUtil.getRequest().getSession().getId();
    }

    public static Integer getDevicesType() {
        Integer devicesType;
        try{
            devicesType = Integer.valueOf(NetWorkUtil.getRequest().getHeader(DEVICES_TYPE));
            if(StringUtils.isEmpty(devicesType)){
                devicesType = Integer.valueOf(NetWorkUtil.getRequest().getParameter(DEVICES_TYPE));
            }
        }catch (Exception e){
            devicesType = null;
        }

        return devicesType;
    }


}
