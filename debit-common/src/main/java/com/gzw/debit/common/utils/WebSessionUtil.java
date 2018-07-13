package com.gzw.debit.common.utils;


import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

/**
 * Created by qinyisheng on 16/9/27.
 */
public class WebSessionUtil {

    private static final String SESSION_ID = "SESSIONID";


    public static HttpSession getSeesion() {
        return NetWorkUtil.getRequest().getSession();
    }

    public static String getSessionId() {
        String sessionId = NetWorkUtil.getRequest().getHeader(SESSION_ID);
        if(StringUtils.isEmpty(sessionId)){
            sessionId = NetWorkUtil.getRequest().getParameter(SESSION_ID);
        }
        return sessionId;
    }

    public static String generSession() {
        String sessionId = UUIDGenerator.getUUID();
        Cookie cookie = new Cookie(SESSION_ID, sessionId);
        cookie.setPath("/");
        NetWorkUtil.getRequest().setAttribute(SESSION_ID, sessionId);
        NetWorkUtil.getResonse().addCookie(cookie);

        return sessionId;
    }

    public static void deleteSession() {
        Cookie cookie = new Cookie(SESSION_ID, null);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        NetWorkUtil.getResonse().addCookie(cookie);
    }
}
