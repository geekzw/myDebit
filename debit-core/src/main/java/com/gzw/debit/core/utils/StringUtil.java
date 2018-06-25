package com.gzw.debit.core.utils;

/**
 * auth:gujian
 * time:2018/6/24
 * email:gujian@maihaoche.com
 * describe:
 */
public class StringUtil {

    public static boolean isEmpty(String string){
        if(string == null || string.equals("")){
            return true;
        }
        return false;
    }

}
