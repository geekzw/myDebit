package com.gzw.debit.common.utils;


import com.gzw.debit.common.entry.HeaderEntry;
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
    private static final String PACKAGE_TYPE = "packageType";



    public static String getSessionId() {
        return getHeaderParamer(SESSION_ID);
    }

    public static String createSessionId(){
        return NetWorkUtil.getRequest().getSession().getId();
    }

    public static String getHeaderParamer(String key) {
        String value;
        try{
            value = NetWorkUtil.getRequest().getHeader(key);
            if(StringUtils.isEmpty(value)){
                value = NetWorkUtil.getRequest().getParameter(key);
            }
        }catch (Exception e){
            value = null;
        }

        return value;
    }


    public static HeaderEntry getHeader(){
        HeaderEntry entry = new HeaderEntry();
        entry.setSessionId(getHeaderParamer(SESSION_ID));
        entry.setDeviceType(Integer.valueOf(getHeaderParamer(DEVICES_TYPE)));
        entry.setPackageType(Integer.valueOf(getHeaderParamer(PACKAGE_TYPE)));
        return entry;
    }


}
